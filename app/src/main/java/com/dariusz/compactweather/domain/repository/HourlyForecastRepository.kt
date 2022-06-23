package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast.Companion.hourlyForecastsToDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HourlyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val hourlyForecastDao: HourlyForecastDao
) {

    suspend fun getFinalTwelveHourForecast(key: String): Flow<List<HourlyForecast>> {
        insertTwelveFourHourForecast(hourlyForecastsToDB(getTwelveHourForecast(key)))
        return hourlyForecastDao.getAllHourlyForecasts()
    }

    private suspend fun getTwelveHourForecast(key: String) =
        compactWeatherApiService.getTwelveHourForecast(key)

    private suspend fun insertTwelveFourHourForecast(inputData: List<HourlyForecast>) {
        hourlyForecastDao.deleteAllHourlyForecasts()
        hourlyForecastDao.insertAll(inputData)
    }

}