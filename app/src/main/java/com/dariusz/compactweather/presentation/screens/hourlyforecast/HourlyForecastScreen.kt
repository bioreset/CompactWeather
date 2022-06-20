package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dariusz.compactweather.presentation.components.common.ScrollableHourlyForecast
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun HourlyForecastScreen(
    cityKey: String,
    hourlyForecastViewModel: HourlyForecastViewModel = hiltViewModel()
) {

    val hourlyForecast = hourlyForecastViewModel.hourlyForecast.collectAsState()

    hourlyForecast.showOnScreen {
        ScrollableHourlyForecast(it)
    }

    LaunchedEffect(Unit) {
        hourlyForecastViewModel.fetchHourlyForecast(cityKey)
    }

}
