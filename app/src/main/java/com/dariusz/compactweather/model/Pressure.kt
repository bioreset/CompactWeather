package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pressure(
    @field:Json(name = "Metric")
    val metric: Metric,
    @field:Json(name = "Imperial")
    val imperial: Imperial
)
