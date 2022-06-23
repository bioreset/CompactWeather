package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import com.dariusz.compactweather.utils.ResultUtils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HourlyForecastViewModel
@Inject
constructor(
    private val hourlyForecastRepository: HourlyForecastRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @OptIn(SavedStateHandleSaveableApi::class)
    private var cityId by savedStateHandle.saveable {
        mutableStateOf("")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val hourlyForecast = snapshotFlow { cityId }
        .flatMapLatest { hourlyForecastRepository.getFinalTwelveHourForecast(it) }
        .asResult(viewModelScope)

    fun fetchHourlyForecast(cityID: String) {
        withMutableSnapshot {
            cityId = cityID
        }
    }

}