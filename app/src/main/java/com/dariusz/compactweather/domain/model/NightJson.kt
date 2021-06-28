package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NightJson(
    @field:Json(name = "ShortPhrase")
    @ColumnInfo(name = "weathercondition")
    val weatherCondition: String,
    @field:Json(name = "ThunderstormProbability")
    @ColumnInfo(name = "thunderstormprobability")
    val thunderstormProbability: Int,
    @field:Json(name = "RainProbability")
    @ColumnInfo(name = "rainprobability")
    val rainProbability: Int,
    @field:Json(name = "SnowProbability")
    @ColumnInfo(name = "snowprobability")
    val snowProbability: Int,
    @field:Json(name = "Wind")
    @Embedded
    val wind: WindV2,
    @field:Json(name = "Rain")
    @Embedded
    val rain: Rain,
    @field:Json(name = "Snow")
    @Embedded
    val snow: Snow,
    @field:Json(name = "HoursOfRain")
    @ColumnInfo(name = "hoursofrain")
    val hoursOfRain: Double,
    @field:Json(name = "HoursOfSnow")
    @ColumnInfo(name = "hoursofsnow")
    val hoursOfSnow: Double,
    @field:Json(name = "CloudCover")
    @ColumnInfo(name = "cloudcover")
    val cloudCover: Int
)
