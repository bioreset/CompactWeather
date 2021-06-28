package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RealFeelTemperature(
    @field:Json(name = "Metric")
    @Embedded
    val metric: Metric,
    @field:Json(name = "Imperial")
    @Embedded
    val imperial: Imperial
)

@JsonClass(generateAdapter = true)
data class RealFeelTemperatureV2(
    @field:Json(name = "Minimum")
    @Embedded
    val minimum: Minimum,
    @field:Json(name = "Maximum")
    @Embedded
    val maximum: Maximum
)

@JsonClass(generateAdapter = true)
data class RealFeelTemperatureV3(
    @field:Json(name = "Value")
    @ColumnInfo(name = "realfeeltempvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "realfeeltempunit")
    val unit: String
)