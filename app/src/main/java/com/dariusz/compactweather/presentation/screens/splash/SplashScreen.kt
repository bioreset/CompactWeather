package com.dariusz.compactweather.presentation.screens.splash

import android.content.Intent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.dariusz.compactweather.di.RepositoryModule.provideCurrentLocationRepository
import com.dariusz.compactweather.di.RepositoryModule.provideRequirementsRepository
import com.dariusz.compactweather.di.RepositoryModule.provideSavedCityRepository
import com.dariusz.compactweather.presentation.MainActivity
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.components.common.LaunchButton
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen
import com.dariusz.compactweather.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@Composable
fun SplashScreen() {

    val currentContext = LocalContext.current

    val mainViewModel = composeViewModel {
        MainViewModel(
            provideCurrentLocationRepository(currentContext),
            provideRequirementsRepository(currentContext)
        )
    }

    val splashScreenViewModel = composeViewModel {
        SplashScreenViewModel(
            provideSavedCityRepository(currentContext)
        )
    }

    val currentLocation by remember(mainViewModel) {
        mainViewModel.currentLocation
    }.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getLocationData()
    }

    ManageDataStateOnScreen(currentLocation) {
        LaunchedEffect(Unit) {
            splashScreenViewModel.manageCities(it)
        }
    }

    LaunchButton {
        currentContext.startActivity(
            Intent(currentContext, MainActivity::class.java)
        )
    }


}