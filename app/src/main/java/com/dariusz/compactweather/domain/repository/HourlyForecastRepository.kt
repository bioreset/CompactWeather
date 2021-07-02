package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast.Companion.hourlyForecastsToDB
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class HourlyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val hourlyForecastDao: HourlyForecastDao
) {

    suspend fun getTwelveFourHourForecast(key: String): Flow<DataState<List<HourlyForecast>>> =
        flow {
            try {
                val hourlyForecast = compactWeatherApiService.getTwelveFourHourForecast(key)
                hourlyForecastDao.insertAll(
                    hourlyForecastsToDB(
                        hourlyForecast
                    )
                )
                val hourlyForecastFromDB = hourlyForecastDao.getAllHourlyForecasts()
                emit(
                    DataState.Success(
                        hourlyForecastFromDB
                    )
                )
            } catch (exception: Exception) {
                emit(DataState.Error(exception))
            }
        }.shareIn(
            MainScope(),
            SharingStarted.Eagerly,
            1
        )
}