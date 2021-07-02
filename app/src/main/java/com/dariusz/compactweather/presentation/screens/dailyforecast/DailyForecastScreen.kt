package com.dariusz.compactweather.presentation.screens.dailyforecast

import android.os.Build
import androidx.annotation.RequiresApi
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
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@Composable
fun DailyForecastScreen(
    city_key: String,
    dailyForecastScreenViewModel: DailyForecastViewModel = viewModel()
) {

    dailyForecastScreenViewModel.fetchDailyForecast(city_key)
    val dailyForecast by remember(dailyForecastScreenViewModel) { dailyForecastScreenViewModel.dailyForecast }.collectAsState()

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