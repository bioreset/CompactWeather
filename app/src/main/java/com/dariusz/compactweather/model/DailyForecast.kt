package com.dariusz.compactweather.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "dailyforecast")
@JsonClass(generateAdapter = true)
data class DailyForecast(
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
    val day: Day,
    @field:Json(name = "Night")
    val night: Night,
    @field:Json(name = "MobileLink")
    val mobileLink: String
)
