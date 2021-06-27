package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temperature(
    @field:Json(name = "Metric")
    val metric: Metric,
    @field:Json(name = "Imperial")
    val imperial: Imperial
)

@JsonClass(generateAdapter = true)
data class TemperatureV2(
    @field:Json(name = "Minimum")
    val minimum: Minimum,
    @field:Json(name = "Maximum")
    val maximum: Maximum
)

@JsonClass(generateAdapter = true)
data class TemperatureV3(
    @field:Json(name = "Value")
    val value: Double,
    @field:Json(name = "Unit")
    val unit: String
)