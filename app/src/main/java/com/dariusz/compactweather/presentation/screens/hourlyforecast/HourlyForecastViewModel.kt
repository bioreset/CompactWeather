package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HourlyForecastViewModel
@Inject
constructor(
    private val hourlyForecastRepository: HourlyForecastRepository
) : ViewModel() {

    private val _hourlyForecast =
        MutableStateFlow<DataState<List<HourlyForecast>>>(DataState.Loading)
    val hourlyForecast: StateFlow<DataState<List<HourlyForecast>>> = _hourlyForecast

    fun fetchHourlyForecast(cityID: String) = viewModelScope.launch {
        hourlyForecastRepository
            .getTwelveFourHourForecast(cityID)
            .collect {
                _hourlyForecast.value = it
            }
    }

}