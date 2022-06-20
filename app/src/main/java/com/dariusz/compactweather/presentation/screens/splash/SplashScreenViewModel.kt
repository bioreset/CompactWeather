package com.dariusz.compactweather.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.Result
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.domain.repository.CurrentLocationRepository
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import com.dariusz.compactweather.utils.ResultUtils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.take
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject constructor(
    private val currentLocationRepository: CurrentLocationRepository,
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val cities: StateFlow<Result<List<SavedCity>>> = currentLocationRepository
        .getCurrentLocation()
        .take(1)
        .flatMapLatest { savedCityRepository.manageCities(it) }
        .asResult(viewModelScope)

}