package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.presentation.components.common.LoadingComponent
import com.dariusz.compactweather.presentation.components.common.ScrollableHourlyForecast
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.presentation.components.theme.getTypography

@Composable
fun HourlyForecastScreen(
    city_key: String,
    hourlyForecastScreenViewModel: HourlyForecastViewModel = viewModel()
) {

    hourlyForecastScreenViewModel.fetchHourlyForecast(city_key)

    val hourlyForecast by remember(hourlyForecastScreenViewModel) {
        hourlyForecastScreenViewModel.hourlyForecast
    }.collectAsState()

    Text(text = "Hourly Forecast", style = ThemeTypography.Main.getTypography().h5)

    ManageHourlyForecast(input = hourlyForecast)

}

@Composable
fun ManageHourlyForecast(input: DataState<List<HourlyForecast>>) {
    when (input) {
        is DataState.Loading -> {
            LoadingComponent()
        }
        is DataState.Success<List<HourlyForecast>> -> {
            ScrollableHourlyForecast(input.data)
        }
        is DataState.Error -> {
            Text("Error: Hourly forecast")
        }
    }
}
