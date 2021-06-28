package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast.Companion.hourlyForecastsToDB
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HourlyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val hourlyForecastDao: HourlyForecastDao
) {

    suspend fun getTwelveFourHourForecast(key: String, networkState: Boolean) = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val hourlyForecast = compactWeatherApiService.getTwelveFourHourForecast(key)
            if (hourlyForecast.toString().isNotEmpty()) {
                if (networkState){
                    hourlyForecastDao.insertAll(
                        hourlyForecastsToDB(
                            hourlyForecast
                        )
                    )
                    delay(500)
                    emit(DataState.Success(hourlyForecast))
                }
                else {
                    emit(DataState.Success(hourlyForecastDao.getAllHourlyForecasts()))
                }
            } else {
                emit(DataState.Error(Exception("Hourly forecast: Data is empty")))
            }
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

}