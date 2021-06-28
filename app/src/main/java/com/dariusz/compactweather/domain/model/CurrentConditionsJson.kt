package com.dariusz.compactweather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentConditionsJson(
    @field:Json(name = "LocalObservationDateTime")
    val datetime: String,
    @field:Json(name = "WeatherText")
    val weatherCondition: String,
    @field:Json(name = "Temperature")
    val temperature: Temperature,
    @field:Json(name = "RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @field:Json(name = "RelativeHumidity")
    val humidity: Int,
    @field:Json(name = "Wind")
    val wind: Wind,
    @field:Json(name = "CloudCover")
    val cloudCover: Int,
    @field:Json(name = "Pressure")
    val pressure: Pressure,
    @field:Json(name = "MobileLink")
    val mobileLink: String
)