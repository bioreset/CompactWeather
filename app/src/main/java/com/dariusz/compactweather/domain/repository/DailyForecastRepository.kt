package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.DailyForecast.Companion.dailyForecastsToDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val dailyForecastDao: DailyForecastDao
) {

    suspend fun getFinalFiveDayForecast(key: String): Flow<List<DailyForecast>> {
        insertFiveDayForecast(dailyForecastsToDB(getFiveDayForecast(key)))
        return dailyForecastDao.getAllDailyForecasts()
    }

    private suspend fun getFiveDayForecast(key: String) =
        compactWeatherApiService.getFiveDayForecast(key).value

    private suspend fun insertFiveDayForecast(insertData: List<DailyForecast>) {
        dailyForecastDao.deleteAllDailyForecasts()
        dailyForecastDao.insertAll(insertData)
    }

}