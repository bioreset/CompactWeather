package com.dariusz.compactweather.presentation.components.navigation


sealed class Screens(val route: String, val title: String) {

    sealed class AppScreens(
        route: String,
        title: String
    ) : Screens(
        route,
        title
    ) {
        object HomeScreen : AppScreens("home", "Home")
        object DailyForecastScreen : AppScreens("dailyforecast", "Daily Forecast")
        object HourlyForecastScreen : AppScreens("hourlyforecast", "Hourly Forecast")
    }
}
