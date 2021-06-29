package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.domain.model.CurrentConditionsJson
import com.dariusz.compactweather.domain.model.DailyForecastResponse
import com.dariusz.compactweather.domain.model.HourlyForecastJson

interface CompactWeatherApiService {

    suspend fun getCurrentWeather(key: String): CurrentConditionsJson

    suspend fun getTwelveFourHourForecast(key: String): List<HourlyForecastJson>

    suspend fun getFiveDayForecast(key: String): DailyForecastResponse

}