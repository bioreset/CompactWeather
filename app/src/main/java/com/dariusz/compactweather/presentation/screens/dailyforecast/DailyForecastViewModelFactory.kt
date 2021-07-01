package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import javax.inject.Inject

class DailyForecastViewModelFactory
@Inject
constructor(
    private val dailyForecastRepository: DailyForecastRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DailyForecastViewModel(
            dailyForecastRepository
        ) as T
    }
}