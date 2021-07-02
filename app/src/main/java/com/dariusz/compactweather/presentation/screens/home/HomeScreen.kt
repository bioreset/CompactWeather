package com.dariusz.compactweather.presentation.screens.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.presentation.components.common.CurrentConditionsPresentation
import com.dariusz.compactweather.presentation.components.common.LoadingComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    navController: NavController
) {

    var cityID = ""

    homeScreenViewModel.getListOfSavedCities()

    val savedCitiesList by remember(homeScreenViewModel) { homeScreenViewModel.listOfSavedCities }.collectAsState()
    val currentConditions by remember(homeScreenViewModel) { homeScreenViewModel.currentConditions }.collectAsState()

    when (savedCitiesList) {
        is DataState.Loading -> {
            LoadingComponent()
        }
        is DataState.Success<*> -> {
            cityID = (savedCitiesList as DataState.Success<List<SavedCity>>).data.first().cityID.toString()
        }
        is DataState.Error -> {
            Text("Error: Home screen saved cities")
        }
    }

    homeScreenViewModel.getCurrentConditions(cityID)

    when (currentConditions) {
        is DataState.Loading -> {
            LoadingComponent()
        }
        is DataState.Success<*> -> {
            CurrentConditionsPresentation(
                navController,
                (savedCitiesList as DataState.Success<List<SavedCity>>).data,
                (currentConditions as DataState.Success<CurrentConditions>).data
            )
        }
        is DataState.Error -> {
            Text("Error: Home screen current conditions")
        }
    }

}