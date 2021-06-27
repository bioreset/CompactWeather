package com.dariusz.compactweather.data.repository

import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.model.DailyForecast
import javax.inject.Inject

class DailyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService
){

    suspend fun getFiveDayForecast(key: String): List<DailyForecast> =
        compactWeatherApiService.getFiveDayForecast(key).value

}