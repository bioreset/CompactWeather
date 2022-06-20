package com.dariusz.compactweather.presentation.components.common

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.dariusz.compactweather.presentation.components.navigation.MainNavigationHost
import com.dariusz.compactweather.presentation.components.theme.CompactWeatherTheme
import com.dariusz.compactweather.presentation.screens.splash.SplashScreen

@Composable
fun CWApp() {
    val currentNavController = rememberNavController()
    CompactWeatherTheme {
        MainAlertBox {
            MainNavigationHost(currentNavController)
        }
    }
}

@Composable
fun CWSplashApp() {
    CompactWeatherTheme {
        MainAlertBox {
            SplashScreen()
        }
    }
}