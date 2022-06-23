package com.dariusz.compactweather.presentation.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.compactweather.presentation.screens.dailyforecast.DailyForecastScreen
import com.dariusz.compactweather.presentation.screens.home.HomeScreen
import com.dariusz.compactweather.presentation.screens.hourlyforecast.HourlyForecastScreen

@Composable
fun MainNavigationHost(
    navController: NavController
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.HomeScreen.route
    ) {
        composable(route = Screens.AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.AppScreens.DailyForecastScreen.route.plus("/{city_key}")) {
            DailyForecastScreen(
                it.arguments?.getString("city_key") ?: ""
            )
        }
        composable(route = Screens.AppScreens.HourlyForecastScreen.route.plus("/{city_key}")) {
            HourlyForecastScreen(
                it.arguments?.getString("city_key") ?: ""
            )
        }
    }
}