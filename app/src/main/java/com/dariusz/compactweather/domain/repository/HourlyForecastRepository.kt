package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast.Companion.hourlyForecastsToDB
import com.dariusz.compactweather.utils.NetworkBoundResource.networkBoundResource
import javax.inject.Inject

interface HourlyForecastRepository {

    suspend fun getFinalTwelveHourForecast(key: String): List<HourlyForecast>

}

class HourlyForecastRepositoryImpl
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val hourlyForecastDao: HourlyForecastDao
) : HourlyForecastRepository {

    override suspend fun getFinalTwelveHourForecast(key: String): List<HourlyForecast> =
        networkBoundResource(
            dataFromNetwork = getTwelveHourForecast(key),
            insertDataFromNetworkToDB = { insertTwelveFourHourForecast(hourlyForecastsToDB(it)) },
            selectFetchedData = getTwelveFourHourForecastFromDB()
        )

    private suspend fun getTwelveHourForecast(key: String) =
        compactWeatherApiService.getTwelveHourForecast(key)

    private suspend fun insertTwelveFourHourForecast(inputData: List<HourlyForecast>) {
        hourlyForecastDao.deleteAllHourlyForecasts()
        hourlyForecastDao.insertAll(inputData)
    }

    private suspend fun getTwelveFourHourForecastFromDB() =
        hourlyForecastDao.getAllHourlyForecasts()
}