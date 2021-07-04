package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.presentation.components.common.LoadingComponent
import com.dariusz.compactweather.presentation.components.common.ScrollableDailyForecast
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.presentation.components.theme.getTypography

@Composable
fun DailyForecastScreen(
    city_key: String,
    dailyForecastScreenViewModel: DailyForecastViewModel = viewModel()
) {

    dailyForecastScreenViewModel.fetchDailyForecast(city_key)

    val dailyForecast by remember(dailyForecastScreenViewModel) {
        dailyForecastScreenViewModel.dailyForecast
    }.collectAsState()

    Text(text = "Daily Forecast", style = ThemeTypography.Main.getTypography().h5)

    ManageDailyForecast(input = dailyForecast)

}

@Composable
fun ManageDailyForecast(input: DataState<List<DailyForecast>>) {
    when (input) {
        is DataState.Loading -> {
            LoadingComponent()
        }
        is DataState.Success -> {
            ScrollableDailyForecast(input.data)
        }
        is DataState.Error -> {
            Text("Error: Hourly forecast")
        }
    }
}
