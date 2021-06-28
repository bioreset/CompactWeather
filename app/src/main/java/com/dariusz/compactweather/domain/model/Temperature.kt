package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temperature(
    @field:Json(name = "Metric")
    @Embedded
    val metric: Metric,
    @field:Json(name = "Imperial")
    @Embedded
    val imperial: Imperial
)

@JsonClass(generateAdapter = true)
data class TemperatureV2(
    @field:Json(name = "Minimum")
    @Embedded
    val minimum: Minimum,
    @field:Json(name = "Maximum")
    @Embedded
    val maximum: Maximum
)

@JsonClass(generateAdapter = true)
data class TemperatureV3(
    @field:Json(name = "Value")
    @ColumnInfo(name = "tempvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "tempunit")
    val unit: String
)