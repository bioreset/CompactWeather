package com.dariusz.compactweather.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    private val _listOfSavedCities =
        MutableStateFlow<DataState<List<SavedCity>>>(DataState.Loading)
    val listOfSavedCities: StateFlow<DataState<List<SavedCity>>> = _listOfSavedCities

    fun getListOfSavedCities() = viewModelScope.launch {
        savedCityRepository
            .listSavedCitiesFromDB()
            .collect {
                _listOfSavedCities.value = it
            }
    }

}