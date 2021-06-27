package com.dariusz.compactweather.data.repository

import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.model.CurrentConditions
import javax.inject.Inject

class CurrentConditionsRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService
){

    suspend fun getCurrentWeather(key: String): CurrentConditions =
        compactWeatherApiService.getCurrentWeather(key)

}