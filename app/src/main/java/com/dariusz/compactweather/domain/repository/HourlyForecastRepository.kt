package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast.Companion.hourlyForecastsToDB
import com.dariusz.compactweather.utils.NetworkBoundResource.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HourlyForecastRepository {

    suspend fun getFinalTwentyFourHourForecast(key: String): Flow<DataState<List<HourlyForecast>>>

}

class HourlyForecastRepositoryImpl
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val hourlyForecastDao: HourlyForecastDao
) : HourlyForecastRepository {

    override suspend fun getFinalTwentyFourHourForecast(key: String): Flow<DataState<List<HourlyForecast>>> =
        networkBoundResource(
            dataFromNetwork = getTwentyFourHourForecast(key),
            insertDataFromNetworkToDB = { insertTwelveFourHourForecast(hourlyForecastsToDB(it)) },
            selectFetchedData = getTwelveFourHourForecastFromDB()
        )

    private suspend fun getTwentyFourHourForecast(key: String) =
        compactWeatherApiService.getTwentyFourHourForecast(key)

    private suspend fun insertTwelveFourHourForecast(inputData: List<HourlyForecast>) {
        hourlyForecastDao.deleteAllHourlyForecasts()
        hourlyForecastDao.insertAll(inputData)
    }

    private suspend fun getTwelveFourHourForecastFromDB() =
        hourlyForecastDao.getAllHourlyForecasts()
}