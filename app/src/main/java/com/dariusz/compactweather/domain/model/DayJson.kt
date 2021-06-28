package com.dariusz.compactweather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayJson(
    @field:Json(name = "ShortPhrase")
    val weatherCondition: String,
    @field:Json(name = "ThunderstormProbability")
    val thunderstormProbability: Int,
    @field:Json(name = "RainProbability")
    val rainProbability: Int,
    @field:Json(name = "SnowProbability")
    val snowProbability: Int,
    @field:Json(name = "Wind")
    val wind: WindV2,
    @field:Json(name = "Rain")
    val rain: Rain,
    @field:Json(name = "Snow")
    val snow: Snow,
    @field:Json(name = "HoursOfRain")
    val hoursOfRain: Double,
    @field:Json(name = "HoursOfSnow")
    val hoursOfSnow: Double,
    @field:Json(name = "CloudCover")
    val cloudCover: Int
)
