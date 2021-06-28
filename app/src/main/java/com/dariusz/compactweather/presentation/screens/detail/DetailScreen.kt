package com.dariusz.compactweather.presentation.screens.detail

import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.repository.RepositoryInjectors.getDailyForecastRepository
import com.dariusz.compactweather.domain.repository.RepositoryInjectors.getHourlyForecastRepository
import com.dariusz.compactweather.presentation.components.LoadingComponent
import com.dariusz.compactweather.presentation.components.ScrollableDailyForecast
import com.dariusz.compactweather.presentation.components.ScrollableHourlyForecast
import com.dariusz.compactweather.presentation.theme.ThemeTypography
import com.dariusz.compactweather.presentation.theme.getTypography

@Composable
fun DetailScreen() {
    val currentContext = LocalContext.current
    val detailScreenState = remember { mutableStateOf("hourly") }
    val detailScreenViewModel = DetailScreenViewModel(
        getDailyForecastRepository(currentContext),
        getHourlyForecastRepository(currentContext)
    )
    detailScreenViewModel.fetchDailyForecast()
    detailScreenViewModel.fetchHourlyForecast()
    val hourlyForecast by remember(detailScreenViewModel) { detailScreenViewModel.hourlyForecast }.collectAsState()
    val dailyForecast by remember(detailScreenViewModel) { detailScreenViewModel.dailyForecast }.collectAsState()
    Switch(
        checked = false,
        onCheckedChange = { detailScreenState.value = "daily" }
    )
    when (detailScreenState.value) {
        "hourly" -> {
            Text(text = "Hourly Forecast", style = ThemeTypography.Main.getTypography().h1)
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
        "daily" -> {
            Text(text = "Daily Forecast", style = ThemeTypography.Main.getTypography().h1)
            when (dailyForecast) {
                is DataState.Loading -> {
                    LoadingComponent()
                }
                is DataState.Success<List<DailyForecast>> -> {
                    ScrollableDailyForecast((dailyForecast as DataState.Success<List<DailyForecast>>).data)
                }
                is DataState.Error -> {
                    Text("Error: Hourly forecast")
                }
            }
        }
    }

}


