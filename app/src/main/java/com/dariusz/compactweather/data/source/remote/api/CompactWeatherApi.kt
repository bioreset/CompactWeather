package com.dariusz.compactweather.data.source.remote.api

import com.dariusz.compactweather.domain.model.AutoComplete
import com.dariusz.compactweather.domain.model.CurrentConditionsJson
import com.dariusz.compactweather.domain.model.DailyForecastResponse
import com.dariusz.compactweather.domain.model.HourlyForecastJson
import com.dariusz.compactweather.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CompactWeatherApi {

    @GET("/locations/v1/cities/autocomplete?")
    suspend fun getAutoComplete(
        @Query("q") q: String,
        @Query("apikey") apiKey: String = API_KEY
    ): List<AutoComplete>


    @GET("currentconditions/v1/{locationkey}")
    suspend fun getCurrentWeather(
        @Path("locationkey") key: String,
        @Query("details") details: Boolean = true,
        @Query("apikey") apiKey: String = API_KEY
    ): CurrentConditionsJson


    @GET("forecasts/v1/hourly/24hour/{locationkey}")
    suspend fun getTwelveFourHourForecast(
        @Path("locationkey") key: String,
        @Query("details") details: Boolean = true,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("metric") metric: Boolean = true
    ): List<HourlyForecastJson>


    @GET("forecasts/v1/daily/5day/{locationkey}")
    suspend fun getFiveDayForecast(
        @Path("locationkey") key: String,
        @Query("details") details: Boolean = true,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("metric") metric: Boolean = true
    ): DailyForecastResponse

}