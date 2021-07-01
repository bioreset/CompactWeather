package com.dariusz.compactweather.presentation.screens.splash

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.components.common.LaunchButton
import com.dariusz.compactweather.presentation.components.navigation.Screens
import com.dariusz.compactweather.utils.NavigationUtils.navigateTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun SplashScreen(
    splashScreenViewModel: SplashScreenViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
    context: Context,
    navController: NavController
) {

    mainViewModel.getLocationData(context)

    splashScreenViewModel.checkSavedCitiesCount()

    val currentLocation by remember(mainViewModel) { mainViewModel.currentLocation }.collectAsState()
    val savedCitiesCount by remember(mainViewModel) { splashScreenViewModel.savedCitiesCount }.collectAsState()

    when (savedCitiesCount) {
        is DataState.Loading -> {
        }
        is DataState.Error -> {
        }
        is DataState.Success<Int> -> {
            if ((savedCitiesCount as DataState.Success<Int>).data == 0) {
                LaunchButton {
                    splashScreenViewModel.addNewCity(currentLocation)
                }.also {
                    ShowMainContentOfSplashScreen(splashScreenViewModel, navController)
                }
            } else {
                navigateTo(navController, Screens.AppScreens.HomeScreen.route)
            }
        }
    }

}

@Composable
fun ShowMainContentOfSplashScreen(
    splashScreenViewModel: SplashScreenViewModel = viewModel(),
    navController: NavController
) {
    val responseAfterAddingCity by remember(splashScreenViewModel) { splashScreenViewModel.responseAfterAddingNewCity }.collectAsState()

    when (responseAfterAddingCity) {
        is DataState.Loading -> {
            Text("Click button below")
        }
        is DataState.Success -> {
            navigateTo(navController, Screens.AppScreens.HomeScreen.route)
        }
        is DataState.Error -> {
            Text("Error: Adding new city")
        }
    }
}
