package com.dariusz.compactweather.presentation.screens.hourlyforecast

import android.os.Build
import androidx.annotation.RequiresApi
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
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@Composable
fun HourlyForecastScreen(
    hourlyForecastScreenViewModel: HourlyForecastViewModel = viewModel()
) {

    hourlyForecastScreenViewModel.fetchHourlyForecast()
    val hourlyForecast by remember(hourlyForecastScreenViewModel) { hourlyForecastScreenViewModel.hourlyForecast }.collectAsState()

    Text(text = "Hourly Forecast", style = ThemeTypography.Main.getTypography().h5)
    when (hourlyForecast) {
        is DataState.Loading -> {
            LoadingComponent()
        }
        is DataState.Success<List<HourlyForecast>> -> {
            ScrollableHourlyForecast((hourlyForecast as DataState.Success<List<HourlyForecast>>).data)
        }
        is DataState.Error -> {
            Text("Error: Hourly forecast")
        }
    }

}
