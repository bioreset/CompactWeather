package com.dariusz.compactweather.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "hourlyforecast")
@JsonClass(generateAdapter = true)
data class HourlyForecast(
    @field:Json(name = "DateTime")
    val dateTime: String,
    @field:Json(name = "IconPhrase")
    val weatherCondition: String,
    @field:Json(name = "Temperature")
    val temperature: TemperatureV3,
    @field:Json(name = "RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperatureV3,
    @field:Json(name = "Wind")
    val windV2: WindV2,
    @field:Json(name = "RelativeHumidity")
    val humidity: Int,
    @field:Json(name = "RainProbability")
    val rainProbability: Int,
    @field:Json(name = "SnowProbability")
    val snowProbability: Int,
    @field:Json(name = "Rain")
    val rain: Rain,
    @field:Json(name = "Snow")
    val snow: Snow,
    @field:Json(name = "CloudCover")
    val cloudCover: Int,
    @field:Json(name = "MobileLink")
    val mobileLink: String
)
