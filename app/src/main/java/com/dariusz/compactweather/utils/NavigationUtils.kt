package com.dariusz.compactweather.utils

import androidx.navigation.NavController

object NavigationUtils {

    fun NavController.navigateToWithArguments(
        screenRoute: String,
        argument: String? = null
    ) {
        navigate(screenRoute.plus("/$argument")) {
            graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
        }
    }


}