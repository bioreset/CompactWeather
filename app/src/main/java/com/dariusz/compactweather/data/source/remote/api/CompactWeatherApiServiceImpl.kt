package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.di.NetworkModule.provideRetrofit
import com.dariusz.compactweather.model.*

class CompactWeatherApiServiceImpl : CompactWeatherApiService {

    private val retrofit = provideRetrofit()

    override suspend fun getAutoComplete(q: String): List<AutoComplete> =
        retrofit.getAutoComplete(q)

    override suspend fun getCurrentWeather(key: String): CurrentConditions =
        retrofit.getCurrentWeather(key)

    override suspend fun getTwelveFourHourForecast(key: String): List<HourlyForecast> =
        retrofit.getTwelveFourHourForecast(key)

    override suspend fun getFiveDayForecast(key: String): DailyForecastResponse =
        retrofit.getFiveDayForecast(key)


}