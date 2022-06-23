package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.utils.ResultUtils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel
@Inject
constructor(
    private val dailyForecastRepository: DailyForecastRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @OptIn(SavedStateHandleSaveableApi::class)
    private var cityId by savedStateHandle.saveable {
        mutableStateOf("")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val dailyForecast = snapshotFlow { cityId }
        .flatMapLatest { dailyForecastRepository.getFinalFiveDayForecast(it) }
        .asResult(viewModelScope)

    fun fetchDailyForecast(cityID: String) {
        withMutableSnapshot {
            cityId = cityID
        }
    }

}