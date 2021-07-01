package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.di.NetworkModule.provideRetrofit
import com.dariusz.compactweather.domain.model.*

class CompactWeatherApiServiceImpl : CompactWeatherApiService {

    private val retrofit = provideRetrofit()

    override suspend fun getCityKeyBasedOnLocation(q: String): SavedCity
        = retrofit.getCityKeyBasedOnLocation(q)

    override suspend fun getCurrentWeather(key: String): CurrentConditionsJson =
        retrofit.getCurrentWeather(key)

    override suspend fun getTwelveFourHourForecast(key: String): List<HourlyForecastJson> =
        retrofit.getTwelveFourHourForecast(key)

    override suspend fun getFiveDayForecast(key: String): DailyForecastResponse =
        retrofit.getFiveDayForecast(key)


}