package com.dariusz.compactweather.utils

import androidx.navigation.NavController

object NavigationUtils {

    fun navigateTo(navController: NavController, screenRoute: String) =
        navController.navigate(screenRoute) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

}