package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel
@Inject
constructor(
    private val dailyForecastRepository: DailyForecastRepository
) : ViewModel() {

    private val _dailyForecast = MutableStateFlow<DataState<List<DailyForecast>>>(DataState.Loading)
    val dailyForecast: StateFlow<DataState<List<DailyForecast>>> = _dailyForecast

    fun fetchDailyForecast() = viewModelScope.launch {
        dailyForecastRepository
            .getFiveDayForecast(Constants.CITY_KEY_FOR_TESTS)
            .collect {
                _dailyForecast.value = it
            }
    }

}