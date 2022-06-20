package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dariusz.compactweather.presentation.components.common.ScrollableDailyForecast
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun DailyForecastScreen(
    cityKey: String,
    dailyForecastViewModel: DailyForecastViewModel = hiltViewModel()
) {

    val dailyForecast = dailyForecastViewModel.dailyForecast.collectAsState()

    dailyForecast.showOnScreen {
        ScrollableDailyForecast(it)
    }

    LaunchedEffect(Unit) {
        dailyForecastViewModel.fetchDailyForecast(cityKey)
    }

}

