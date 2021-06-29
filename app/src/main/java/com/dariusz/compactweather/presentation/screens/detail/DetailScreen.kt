package com.dariusz.compactweather.presentation.screens.detail

import android.util.Log
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Preview(showBackground = true)
@Composable
fun DetailScreen() {
    val currentContext = LocalContext.current
    val detailScreenState = remember { mutableStateOf("hourly") }
    val switchState = remember (Boolean) { mutableStateOf(true) }
    Log.e("DETAIL_SCREEN_PROGRESS", "remembering state")
    val detailScreenViewModel: DetailScreenViewModel = viewModel(
        factory = DetailScreenViewModelFactory(
            getDailyForecastRepository(currentContext),
            getHourlyForecastRepository(currentContext)
        )
    )
    Log.e("DETAIL_SCREEN_PROGRESS", "view model init")
    detailScreenViewModel.fetchDailyForecast()
    detailScreenViewModel.fetchHourlyForecast()
    Log.e("DETAIL_SCREEN_PROGRESS", "fetch init")
    val hourlyForecast by remember(detailScreenViewModel) { detailScreenViewModel.hourlyForecast }.collectAsState()
    val dailyForecast by remember(detailScreenViewModel) { detailScreenViewModel.dailyForecast }.collectAsState()
    Log.e("DETAIL_SCREEN_PROGRESS", hourlyForecast.toString())
    Log.e("DETAIL_SCREEN_PROGRESS", dailyForecast.toString())
    Switch(
        checked = switchState.value,
        onCheckedChange = { if (switchState.value) detailScreenState.value = "hourly" else detailScreenState.value = "daily" },
    )
    Log.e("DETAIL_SCREEN_SWITCH_STATE", switchState.value.toString())
    when (detailScreenState.value) {
        "hourly" -> {
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
        "daily" -> {
            Text(text = "Daily Forecast", style = ThemeTypography.Main.getTypography().h5)
            when (dailyForecast) {
                is DataState.Loading -> {
                    LoadingComponent()
                }
                is DataState.Success -> {
                    ScrollableDailyForecast((dailyForecast as DataState.Success<List<DailyForecast>>).data)
                }
                is DataState.Error -> {
                    Text("Error: Hourly forecast")
                }
            }
        }
    }

}


