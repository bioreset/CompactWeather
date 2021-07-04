package com.dariusz.compactweather.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.domain.repository.CurrentConditionsRepository
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val currentConditionsRepository: CurrentConditionsRepository,
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    private val _listOfSavedCities =
        MutableStateFlow<DataState<List<SavedCity>>>(DataState.Loading)
    val listOfSavedCities: StateFlow<DataState<List<SavedCity>>> = _listOfSavedCities
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DataState.Loading)

    private val _currentConditions =
        MutableStateFlow<DataState<List<CurrentConditions>>>(DataState.Loading)
    val currentConditions: StateFlow<DataState<List<CurrentConditions>>> = _currentConditions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DataState.Loading)

    fun manageCities(location: CurrentLocation) = viewModelScope.launch {
        savedCityRepository
            .manageCities(location)
            .collect {
                _listOfSavedCities.value = it
            }
    }

    fun getCurrentConditions(cityID: String) = viewModelScope.launch {
        currentConditionsRepository
            .getCurrentConditionsData(cityID)
            .collect {
                _currentConditions.value = it
            }
    }

}