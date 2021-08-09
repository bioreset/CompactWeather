package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.lifecycle.ViewModel
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import com.dariusz.compactweather.utils.ViewModelUtils.launchVMTask
import com.dariusz.compactweather.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HourlyForecastViewModel
@Inject
constructor(
    private val hourlyForecastRepository: HourlyForecastRepository
) : ViewModel() {

    private val _hourlyForecast =
        MutableStateFlow<DataState<List<HourlyForecast>>>(DataState.Idle)
    val hourlyForecast: StateFlow<DataState<List<HourlyForecast>>> = _hourlyForecast

    fun fetchHourlyForecast(cityID: String) = launchVMTask {
        manageResult(
            _hourlyForecast,
            hourlyForecastRepository.getFinalTwelveHourForecast(cityID)
        )
    }

}