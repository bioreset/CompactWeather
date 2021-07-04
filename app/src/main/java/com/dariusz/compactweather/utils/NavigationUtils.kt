package com.dariusz.compactweather.utils

import androidx.navigation.NavController

object NavigationUtils {

    fun navigateToWithArguments(
        navController: NavController,
        screenRoute: String,
        argument: String? = null
    ) {
        navController.navigate(screenRoute.plus("/$argument")) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }


}