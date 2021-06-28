package com.dariusz.compactweather.presentation.navigation


sealed class Screens(val route: String, val title: String) {

    sealed class AppScreens(
        route: String,
        title: String
    ) : Screens(
        route,
        title
    ) {
        object SplashScreen : AppScreens("splash", "Splash")
        object HomeScreen : AppScreens("home", "Home")
        object DetailsScreen : AppScreens("details", "Details")
        object SettingsScreen : AppScreens("settings", "Settings")
    }
}

val screensBottomNav = listOf(
    Screens.AppScreens.SplashScreen,
    Screens.AppScreens.HomeScreen,
    Screens.AppScreens.DetailsScreen,
    Screens.AppScreens.SettingsScreen
)