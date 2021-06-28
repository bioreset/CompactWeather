package com.dariusz.compactweather.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import com.dariusz.compactweather.utils.Constants.CITY_KEY_FOR_TESTS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel
@Inject
constructor(
    private val dailyForecastRepository: DailyForecastRepository,
    private val hourlyForecastRepository: HourlyForecastRepository
) : ViewModel() {

    private val _hourlyForecast =
        MutableStateFlow<DataState<List<HourlyForecast>>>(DataState.Loading)
    val hourlyForecast: StateFlow<DataState<List<HourlyForecast>>> = _hourlyForecast

    private val _dailyForecast = MutableStateFlow<DataState<List<DailyForecast>>>(DataState.Loading)
    val dailyForecast: StateFlow<DataState<List<DailyForecast>>> = _dailyForecast

    fun fetchHourlyForecast() = viewModelScope.launch {
        hourlyForecastRepository
            .getTwelveFourHourForecast(CITY_KEY_FOR_TESTS)
            .collect {
                _hourlyForecast.value = it
            }
    }

    fun fetchDailyForecast() = viewModelScope.launch {
        dailyForecastRepository
            .getFiveDayForecast(CITY_KEY_FOR_TESTS)
            .collect {
                _dailyForecast.value = it
            }
    }
}