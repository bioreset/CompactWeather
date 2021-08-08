package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.di.NetworkModule.provideRetrofit
import com.dariusz.compactweather.domain.model.CurrentConditionsJson
import com.dariusz.compactweather.domain.model.DailyForecastResponse
import com.dariusz.compactweather.domain.model.HourlyForecastJson
import com.dariusz.compactweather.domain.model.SavedCity

interface CompactWeatherApiService {

    suspend fun getCityKeyBasedOnLocation(q: String): SavedCity

    suspend fun getCurrentWeather(key: String): List<CurrentConditionsJson>

    suspend fun getTwentyFourHourForecast(key: String): List<HourlyForecastJson>

    suspend fun getFiveDayForecast(key: String): DailyForecastResponse

}

class CompactWeatherApiServiceImpl : CompactWeatherApiService {

    private val retrofit = provideRetrofit()

    override suspend fun getCityKeyBasedOnLocation(q: String): SavedCity =
        retrofit.getCityKeyBasedOnLocation(q)

    override suspend fun getCurrentWeather(key: String): List<CurrentConditionsJson> =
        retrofit.getCurrentWeather(key)

    override suspend fun getTwentyFourHourForecast(key: String): List<HourlyForecastJson> =
        retrofit.getTwentyFourHourForecast(key)

    override suspend fun getFiveDayForecast(key: String): DailyForecastResponse =
        retrofit.getFiveDayForecast(key)


}