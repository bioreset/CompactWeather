package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DataState.Loading)

    fun fetchDailyForecast(cityID: String) = viewModelScope.launch {
        dailyForecastRepository
            .getFinalFiveDayForecast(cityID)
            .collect {
                _dailyForecast.value = it
            }
    }

}