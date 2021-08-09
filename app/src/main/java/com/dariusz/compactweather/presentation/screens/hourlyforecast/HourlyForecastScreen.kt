package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dariusz.compactweather.di.RepositoryModule.provideHourlyForecastRepository
import com.dariusz.compactweather.presentation.components.common.ScrollableHourlyForecast
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen
import com.dariusz.compactweather.utils.ScreenUtils.ShowScreen

@Composable
fun HourlyForecastScreen(
    cityKey: String
) {

    ShowScreen(
        viewModel = { context ->
            HourlyForecastViewModel(
                provideHourlyForecastRepository(context)
            )
        },
        data = { viewModel ->
            viewModel.hourlyForecast
        },
        composable = { dataState ->
            Text(text = "Hourly Forecast", style = ThemeTypography.h5)
            ManageDataStateOnScreen(dataState) {
                ScrollableHourlyForecast(it)
            }
        },
        launchedEffect = { viewModel ->
            viewModel.fetchHourlyForecast(cityKey)
        }
    )

}
