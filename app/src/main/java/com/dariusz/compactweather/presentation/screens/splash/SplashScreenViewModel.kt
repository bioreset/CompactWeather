package com.dariusz.compactweather.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject
constructor(
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    private val _responseAfterAddingNewCity =
        MutableStateFlow<DataState<Boolean>>(DataState.Loading)
    val responseAfterAddingNewCity: StateFlow<DataState<Boolean>> = _responseAfterAddingNewCity

    private val _savedCitiesCount = MutableStateFlow<DataState<Int>>(DataState.Loading)
    val savedCitiesCount: StateFlow<DataState<Int>> = _savedCitiesCount

    fun addNewCity(location: CurrentLocation) = viewModelScope.launch {
        savedCityRepository
            .insertCity(location)
            .collect {
                _responseAfterAddingNewCity.value = it
            }
    }

    fun checkSavedCitiesCount() = viewModelScope.launch {
        savedCityRepository
            .countCitiesInDB()
            .collect {
                _savedCitiesCount.value = it
            }
    }

}