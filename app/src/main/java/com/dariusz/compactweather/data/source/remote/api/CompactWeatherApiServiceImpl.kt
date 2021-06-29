package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.di.NetworkModule.provideRetrofit
import com.dariusz.compactweather.domain.model.AutoComplete
import com.dariusz.compactweather.domain.model.CurrentConditionsJson
import com.dariusz.compactweather.domain.model.DailyForecastResponse
import com.dariusz.compactweather.domain.model.HourlyForecastJson

class CompactWeatherApiServiceImpl : CompactWeatherApiService {

    private val retrofit = provideRetrofit()

    override suspend fun getCurrentWeather(key: String): CurrentConditionsJson =
        retrofit.getCurrentWeather(key)

    override suspend fun getTwelveFourHourForecast(key: String): List<HourlyForecastJson> =
        retrofit.getTwelveFourHourForecast(key)

    override suspend fun getFiveDayForecast(key: String): DailyForecastResponse =
        retrofit.getFiveDayForecast(key)


}