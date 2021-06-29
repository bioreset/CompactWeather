package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.CurrentConditions.Companion.currentConditionsToDB
import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class CurrentConditionsRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val currentConditionsDao: CurrentConditionsDao
) {

    suspend fun getCurrentWeather(key: String) = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val currentWeather = compactWeatherApiService.getCurrentWeather(key)
            currentConditionsDao.insertAll(currentConditionsToDB(currentWeather))
            val currentWeatherFromDB = currentConditionsDao.getAllCurrentConditions()
            delay(500)
            emit(DataState.Success(currentWeatherFromDB))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }.shareIn(
        MainScope(),
        SharingStarted.WhileSubscribed(),
        1
    )
}