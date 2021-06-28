package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast.Companion.hourlyForecastsToDB
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HourlyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val hourlyForecastDao: HourlyForecastDao
) {

    suspend fun getTwelveFourHourForecast(key: String): Flow<DataState<List<HourlyForecast>>> =
        flow {
            emit(DataState.Loading)
            delay(1000)
            try {
                val hourlyForecast = compactWeatherApiService.getTwelveFourHourForecast(key)
                hourlyForecastDao.insertAll(
                    hourlyForecastsToDB(
                        hourlyForecast
                    )
                )
                delay(500)
                emit(
                    DataState.Success(
                        hourlyForecastsToDB(
                            hourlyForecast
                        )
                    )
                )

            } catch (exception: Exception) {
                emit(DataState.Error(exception))
            }
        }

}