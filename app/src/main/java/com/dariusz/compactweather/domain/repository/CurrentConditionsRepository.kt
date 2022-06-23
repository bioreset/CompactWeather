package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.CurrentConditions.Companion.currentConditionsToDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentConditionsRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val currentConditionsDao: CurrentConditionsDao
) {

    suspend fun getCurrentConditionsData(key: String): Flow<CurrentConditions> {
        insertCurrentConditions(currentConditionsToDB(getCurrentWeather(key)[0]))
        return currentConditionsDao.getAllCurrentConditions()
    }

    private suspend fun getCurrentWeather(key: String) =
        compactWeatherApiService.getCurrentWeather(key)

    private suspend fun insertCurrentConditions(currentConditions: CurrentConditions) {
        currentConditionsDao.deleteAllCurrentConditions()
        currentConditionsDao.insert(currentConditions)
    }

}