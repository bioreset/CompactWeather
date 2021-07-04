package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.CurrentConditions.Companion.currentConditionsToDB
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.utils.NetworkBoundResource.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CurrentConditionsRepository {

    suspend fun getCurrentConditionsData(key: String): Flow<DataState<List<CurrentConditions>>>

}

class CurrentConditionsRepositoryImpl
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val currentConditionsDao: CurrentConditionsDao
) : CurrentConditionsRepository {

    override suspend fun getCurrentConditionsData(key: String): Flow<DataState<List<CurrentConditions>>> =
        networkBoundResource(
            dataFromNetwork = getCurrentWeather(key),
            insertDataFromNetworkToDB = { insertCurrentConditions(currentConditionsToDB(it)) },
            selectFetchedData = getCurrentWeatherFromDB()
        )

    private suspend fun getCurrentWeather(key: String) =
        compactWeatherApiService.getCurrentWeather(key)

    private suspend fun insertCurrentConditions(currentConditions: List<CurrentConditions>) {
        currentConditionsDao.deleteAllCurrentConditions()
        currentConditionsDao.insertAll(currentConditions)
    }

    private suspend fun getCurrentWeatherFromDB() =
        currentConditionsDao.getAllCurrentConditions()

}