package com.dariusz.compactweather.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dariusz.compactweather.presentation.components.common.CurrentConditionsPresentation
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val homeScreenData = homeScreenViewModel.homeScreenData.collectAsState()

    homeScreenData.showOnScreen {
        CurrentConditionsPresentation(
            navController = navController,
            savedCity = it.savedCity,
            input = it.currentConditions
        )
    }

}