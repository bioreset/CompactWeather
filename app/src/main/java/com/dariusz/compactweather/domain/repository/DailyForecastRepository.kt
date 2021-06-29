package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DailyForecast.Companion.dailyForecastsToDB
import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class DailyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val dailyForecastDao: DailyForecastDao
) {

    suspend fun getFiveDayForecast(key: String) = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val dailyForecast = compactWeatherApiService.getFiveDayForecast(key).value
            dailyForecastDao.insertAll(
                dailyForecastsToDB(
                    dailyForecast
                )
            )
            val dailyForecastFromDB = dailyForecastDao.getAllDailyForecasts()
            delay(500)
            emit(
                DataState.Success(
                    dailyForecastFromDB
                )
            )
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }.shareIn(
        MainScope(),
        SharingStarted.WhileSubscribed(),
        1
    )
}