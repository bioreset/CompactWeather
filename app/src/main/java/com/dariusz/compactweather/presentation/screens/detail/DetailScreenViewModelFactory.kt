package com.dariusz.compactweather.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import javax.inject.Inject

class DetailScreenViewModelFactory
@Inject
constructor(
    private val dailyForecastRepository: DailyForecastRepository,
    private val hourlyForecastRepository: HourlyForecastRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailScreenViewModel(
            dailyForecastRepository,
            hourlyForecastRepository
        ) as T
    }
}