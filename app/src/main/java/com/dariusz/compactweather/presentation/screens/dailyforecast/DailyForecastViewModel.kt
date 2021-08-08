package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.lifecycle.ViewModel
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.utils.ViewModelUtils.launchVMTask
import com.dariusz.compactweather.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel
@Inject
constructor(
    private val dailyForecastRepository: DailyForecastRepository
) : ViewModel() {

    private val _dailyForecast = MutableStateFlow<DataState<List<DailyForecast>>>(DataState.Idle)
    val dailyForecast: StateFlow<DataState<List<DailyForecast>>> = _dailyForecast

    fun fetchDailyForecast(cityID: String) = launchVMTask {
        manageResult(
            _dailyForecast,
            dailyForecastRepository.getFinalFiveDayForecast(cityID)
        )
    }

}