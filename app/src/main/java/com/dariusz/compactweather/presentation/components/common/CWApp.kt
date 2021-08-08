package com.dariusz.compactweather.presentation.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.dariusz.compactweather.di.RepositoryModule.provideCurrentLocationRepository
import com.dariusz.compactweather.di.RepositoryModule.provideRequirementsRepository
import com.dariusz.compactweather.domain.model.AppTheme
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.components.navigation.MainNavigationHost
import com.dariusz.compactweather.presentation.components.theme.CompactWeatherTheme
import com.dariusz.compactweather.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun CWApp() {
    val theme by remember { mutableStateOf(AppTheme()) }
    val currentNavController = rememberNavController()
    val currentContext = LocalContext.current
    val mainViewModel = composeViewModel {
        MainViewModel(
            provideCurrentLocationRepository(currentContext),
            provideRequirementsRepository(currentContext)
        )
    }
    CompactWeatherTheme(theme) {
        MainNavigationHost(currentNavController, currentContext, mainViewModel)
        MainAlertBox(mainViewModel)
    }
}
