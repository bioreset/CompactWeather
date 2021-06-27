package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.model.*

interface CompactWeatherApiService {

    suspend fun getAutoComplete(q: String): List<AutoComplete>

    suspend fun getCurrentWeather(key: String): CurrentConditions

    suspend fun getTwelveFourHourForecast(key: String): List<HourlyForecast>

    suspend fun getFiveDayForecast(key: String): DailyForecastResponse

}