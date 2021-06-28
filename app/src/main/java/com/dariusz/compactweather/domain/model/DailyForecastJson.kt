package com.dariusz.compactweather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecastJson(
    @field:Json(name = "Date")
    val dateTime: String,
    @field:Json(name = "Sun")
    val sun: Sun,
    @field:Json(name = "Moon")
    val moon: Moon,
    @field:Json(name = "Temperature")
    val temperature: TemperatureV2,
    @field:Json(name = "RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperatureV2,
    @field:Json(name = "HoursOfSun")
    val hoursOfSun: Int,
    @field:Json(name = "Day")
    val dayJson: DayJson,
    @field:Json(name = "Night")
    val nightJson: NightJson,
    @field:Json(name = "MobileLink")
    val mobileLink: String
)
