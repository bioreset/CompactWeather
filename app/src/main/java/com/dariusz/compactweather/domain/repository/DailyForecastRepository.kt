package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DailyForecast.Companion.dailyForecastsToDB
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.utils.NetworkBoundResource.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DailyForecastRepository {

    suspend fun getFinalFiveDayForecast(key: String): Flow<DataState<List<DailyForecast>>>

}

class DailyForecastRepositoryImpl
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val dailyForecastDao: DailyForecastDao
) : DailyForecastRepository {

    override suspend fun getFinalFiveDayForecast(key: String): Flow<DataState<List<DailyForecast>>> =
        networkBoundResource(
            dataFromNetwork = getFiveDayForecast(key),
            insertDataFromNetworkToDB = { insertFiveDayForecast(dailyForecastsToDB(it)) },
            selectFetchedData = getFiveDayForecastFromDB()
        )

    private suspend fun getFiveDayForecast(key: String) =
        compactWeatherApiService.getFiveDayForecast(key).value

    private suspend fun insertFiveDayForecast(insertData: List<DailyForecast>) {
        dailyForecastDao.deleteAllDailyForecasts()
        dailyForecastDao.insertAll(insertData)
    }

    private suspend fun getFiveDayForecastFromDB() =
        dailyForecastDao.getAllDailyForecasts()

}