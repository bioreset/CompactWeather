package com.dariusz.compactweather.presentation.navigation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavigatorButton(navController: NavController, screens: Screens) {
    Button(onClick = {
        navController.navigate(screens.route) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    })
    {
        Text("Navigate to details")
    }
}
