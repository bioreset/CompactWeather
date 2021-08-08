package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.dariusz.compactweather.presentation.components.common.ScrollableHourlyForecast
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen

@Composable
fun HourlyForecastScreen(
    cityKey: String,
    hourlyForecastScreenViewModel: HourlyForecastViewModel
) {

    val hourlyForecast by remember(hourlyForecastScreenViewModel) {
        hourlyForecastScreenViewModel.hourlyForecast
    }.collectAsState()

    Text(text = "Hourly Forecast", style = ThemeTypography.h5)

    ManageDataStateOnScreen(hourlyForecast) {
        ScrollableHourlyForecast(it)
    }

    LaunchedEffect(Unit) {
        hourlyForecastScreenViewModel.fetchHourlyForecast(cityKey)
    }

}
