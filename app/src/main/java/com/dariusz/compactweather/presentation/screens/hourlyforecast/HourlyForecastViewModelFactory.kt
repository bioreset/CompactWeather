package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import javax.inject.Inject

class HourlyForecastViewModelFactory
@Inject
constructor(
    private val hourlyForecastRepository: HourlyForecastRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HourlyForecastViewModel(
            hourlyForecastRepository
        ) as T
    }
}