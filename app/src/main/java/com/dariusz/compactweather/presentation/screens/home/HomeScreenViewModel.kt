package com.dariusz.compactweather.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.domain.repository.CurrentConditionsRepository
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import com.dariusz.compactweather.utils.ViewModelUtils.launchVMTask
import com.dariusz.compactweather.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val currentConditionsRepository: CurrentConditionsRepository,
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    private val _listOfSavedCities =
        MutableStateFlow<DataState<List<SavedCity>>>(DataState.Idle)
    val listOfSavedCities: StateFlow<DataState<List<SavedCity>>> = _listOfSavedCities

    private val _currentConditions =
        MutableStateFlow<DataState<List<CurrentConditions>>>(DataState.Idle)
    val currentConditions: StateFlow<DataState<List<CurrentConditions>>> = _currentConditions

    fun manageCities(location: CurrentLocation) = launchVMTask {
        manageResult(
            _listOfSavedCities,
            savedCityRepository.manageCities(location)
        )
    }

    fun getCurrentConditions(cityID: String) = launchVMTask {
        manageResult(
            _currentConditions,
            currentConditionsRepository.getCurrentConditionsData(cityID)
        )
    }

}