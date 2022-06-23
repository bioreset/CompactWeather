package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.domain.model.CurrentConditionsJson
import com.dariusz.compactweather.domain.model.DailyForecastResponse
import com.dariusz.compactweather.domain.model.HourlyForecastJson
import com.dariusz.compactweather.domain.model.SavedCity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompactWeatherApiService @Inject constructor(
    private val retrofit: CompactWeatherApi
) {

    suspend fun getCityKeyBasedOnLocation(q: String): SavedCity =
        retrofit.getCityKeyBasedOnLocation(q)

    suspend fun getCurrentWeather(key: String): List<CurrentConditionsJson> =
        retrofit.getCurrentWeather(key)

    suspend fun getTwelveHourForecast(key: String): List<HourlyForecastJson> =
        retrofit.getTwelveHourForecast(key)

    suspend fun getFiveDayForecast(key: String): DailyForecastResponse =
        retrofit.getFiveDayForecast(key)

}