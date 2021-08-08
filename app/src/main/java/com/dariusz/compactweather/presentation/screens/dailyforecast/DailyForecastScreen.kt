package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.dariusz.compactweather.presentation.components.common.ScrollableDailyForecast
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen

@Composable
fun DailyForecastScreen(
    cityKey: String,
    dailyForecastScreenViewModel: DailyForecastViewModel
) {

    val dailyForecast by remember(dailyForecastScreenViewModel) {
        dailyForecastScreenViewModel.dailyForecast
    }.collectAsState()

    Text(text = "Daily Forecast", style = ThemeTypography.h5)

    ManageDataStateOnScreen(dailyForecast) {
        ScrollableDailyForecast(it)
    }

    LaunchedEffect(Unit) {
        dailyForecastScreenViewModel.fetchDailyForecast(cityKey)
    }

}

