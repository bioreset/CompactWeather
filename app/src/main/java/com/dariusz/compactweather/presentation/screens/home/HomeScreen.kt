package com.dariusz.compactweather.presentation.screens.home

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.components.common.CurrentConditionsPresentation
import com.dariusz.compactweather.presentation.components.common.LoadingComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
    currentContext: Context,
    navController: NavController
) {

    mainViewModel.getLocationData(currentContext)

    val lifecycleOwnerScope = LocalLifecycleOwner.current.lifecycleScope

    LaunchedEffect(lifecycleOwnerScope.coroutineContext) {
        mainViewModel.currentLocation.collect {
            homeScreenViewModel.manageCities(it)
        }
    }

    val savedCitiesListFinal by remember(homeScreenViewModel) {
        homeScreenViewModel.listOfSavedCities
    }.collectAsState()

    val currentConditionsFinal by remember(homeScreenViewModel) {
        homeScreenViewModel.currentConditions
    }.collectAsState()

    ManageHomeScreen(
        homeScreenViewModel = homeScreenViewModel,
        inputOne = savedCitiesListFinal,
        inputTwo = currentConditionsFinal,
        navController = navController
    )

}

@Composable
fun ManageHomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    inputOne: DataState<List<SavedCity>>,
    inputTwo: DataState<List<CurrentConditions>>,
    navController: NavController
) {

    when (inputOne) {
        is DataState.Loading -> {
            LoadingComponent()
        }
        is DataState.Success<List<SavedCity>> -> {
            val output = inputOne.data
            when (output.size) {
                1 -> {
                    homeScreenViewModel.getCurrentConditions(output.first().cityID.toString())
                    when (inputTwo) {
                        is DataState.Loading -> {
                            LoadingComponent()
                        }
                        is DataState.Success<List<CurrentConditions>> -> {
                            CurrentConditionsPresentation(
                                navController,
                                output,
                                inputTwo.data.first()
                            )
                        }
                        is DataState.Error -> {
                            Text("Error: Home screen current conditions")
                        }
                    }
                }
                else -> {
                    Text("Add city")
                }
            }
        }
        is DataState.Error -> {
            Text("Error: Home screen saved cities")
        }
    }

}
