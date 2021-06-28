package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DailyForecast.Companion.dailyForecastsToDB
import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DailyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val dailyForecastDao: DailyForecastDao
) {

    suspend fun getFiveDayForecast(key: String, networkState: Boolean) = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val dailyForecast = compactWeatherApiService.getFiveDayForecast(key).value
            if (dailyForecast.toString().isNotEmpty()) {
                if (networkState) {
                    dailyForecastDao.insertAll(
                        dailyForecastsToDB(
                            dailyForecast
                        )
                    )
                    delay(500)
                    emit(DataState.Success(dailyForecast))
                }
                else {
                    emit(DataState.Success(dailyForecastDao.getAllDailyForecasts()))
                }
            } else {
                emit(DataState.Error(Exception("Daily forecast: Data is empty")))
            }
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

}