package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @field:Json(name = "Direction")
    val direction: Direction,
    @field:Json(name = "Speed")
    val speed: Speed
)

@JsonClass(generateAdapter = true)
data class WindV2(
    @field:Json(name = "Direction")
    val direction: Direction,
    @field:Json(name = "Speed")
    val speed: SpeedV2
)

@JsonClass(generateAdapter = true)
data class Speed(
    @field:Json(name = "Metric")
    val metric: Metric,
    @field:Json(name = "Imperial")
    val imperial: Imperial
)

@JsonClass(generateAdapter = true)
data class SpeedV2(
    @field:Json(name = "Value")
    val value: Double,
    @field:Json(name = "Unit")
    val unit: String
)