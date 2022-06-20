package com.dariusz.compactweather.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.Result
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.domain.repository.CurrentConditionsRepository
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import com.dariusz.compactweather.utils.ResultUtils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val currentConditionsRepository: CurrentConditionsRepository,
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    private var cityId: Flow<String> = savedCityRepository
        .getSavedCities()
        .map { it[0].cityID.toString() }

    val homeScreenData: StateFlow<Result<HomeScreenData>> = combine(
        cityId.flatMapLatest { currentConditionsRepository.getCurrentConditionsData(it) },
        savedCityRepository.getSavedCities().map { it[0] }
    ) { currentConditions, savedCities ->
        HomeScreenData(currentConditions, savedCities)
    }
        .asResult(viewModelScope)

}

data class HomeScreenData(
    val currentConditions: CurrentConditions,
    val savedCity: SavedCity
)