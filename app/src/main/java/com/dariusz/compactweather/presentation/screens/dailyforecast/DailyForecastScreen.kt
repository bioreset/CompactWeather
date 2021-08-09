package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dariusz.compactweather.di.RepositoryModule.provideDailyForecastRepository
import com.dariusz.compactweather.presentation.components.common.ScrollableDailyForecast
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen
import com.dariusz.compactweather.utils.ScreenUtils.ShowScreen

@Composable
fun DailyForecastScreen(
    cityKey: String
) {

    ShowScreen(
        viewModel = { context ->
            DailyForecastViewModel(
                provideDailyForecastRepository(context)
            )
        },
        data = { viewModel ->
            viewModel.dailyForecast
        },
        composable = { dataState ->
            Text(text = "Daily Forecast", style = ThemeTypography.h5)
            ManageDataStateOnScreen(dataState) {
                ScrollableDailyForecast(it)
            }
        },
        launchedEffect = { viewModel ->
            viewModel.fetchDailyForecast(cityKey)
        }
    )

}

