package com.dariusz.compactweather.presentation.screens.home

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.dariusz.compactweather.di.RepositoryModule.provideCurrentConditionsRepository
import com.dariusz.compactweather.di.RepositoryModule.provideSavedCityRepository
import com.dariusz.compactweather.presentation.components.common.CurrentConditionsPresentation
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStatesOnScreen
import com.dariusz.compactweather.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    navController: NavController
) {

    val currentContext = LocalContext.current

    val homeScreenViewModel = composeViewModel {
        HomeScreenViewModel(
            provideCurrentConditionsRepository(currentContext),
            provideSavedCityRepository(currentContext)
        )
    }

    val savedCitiesListFinal by remember(homeScreenViewModel) {
        homeScreenViewModel.listOfSavedCities
    }.collectAsState()

    val currentConditionsFinal by remember(homeScreenViewModel) {
        homeScreenViewModel.currentConditions
    }.collectAsState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.getCitiesID()
    }

    ManageDataStateOnScreen(savedCitiesListFinal) {
        LaunchedEffect(Unit) {
            homeScreenViewModel.getCurrentConditions(it.first().cityID.toString())
        }
    }

    ManageDataStatesOnScreen(
        savedCitiesListFinal,
        currentConditionsFinal
    ) { savedCities, conditions ->
        CurrentConditionsPresentation(
            navController,
            savedCities,
            conditions.first()
        )
    }


}