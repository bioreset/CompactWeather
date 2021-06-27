package com.dariusz.compactweather.data.repository

import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.model.HourlyForecast
import javax.inject.Inject

class HourlyForecastRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService
){

    suspend fun getTwelveFourHourForecast(key: String): List<HourlyForecast> =
        compactWeatherApiService.getTwelveFourHourForecast(key)

}