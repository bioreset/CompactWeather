package com.dariusz.compactweather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.compactweather.presentation.screens.detail.DetailScreen
import com.dariusz.compactweather.presentation.screens.home.HomeScreen
import com.dariusz.compactweather.presentation.screens.home.HomeScreenViewModel
import com.dariusz.compactweather.presentation.screens.settings.SettingsScreen
import com.dariusz.compactweather.presentation.screens.settings.SettingsScreenViewModel
import com.dariusz.compactweather.presentation.screens.splash.SplashScreen
import com.dariusz.compactweather.presentation.screens.splash.SplashScreenViewModel

@Composable
fun MainNavigationHost(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.SplashScreen.route
    ) {
        composable(Screens.AppScreens.SplashScreen.route) {
            val viewModel: SplashScreenViewModel = viewModel()
            SplashScreen(viewModel)
        }
        composable(Screens.AppScreens.HomeScreen.route) {
            val viewModel: HomeScreenViewModel = viewModel()
            HomeScreen(viewModel)
        }
        composable(Screens.AppScreens.DetailsScreen.route) {
            DetailScreen()
        }
        composable(Screens.AppScreens.SettingsScreen.route) {
            val viewModel: SettingsScreenViewModel = viewModel()
            SettingsScreen(viewModel)
        }
    }
}