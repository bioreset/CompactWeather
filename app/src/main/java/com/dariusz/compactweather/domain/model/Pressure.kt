package com.dariusz.compactweather.domain.model

import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pressure(
    @field:Json(name = "Metric")
    @Embedded
    val metric: Metric,
    @field:Json(name = "Imperial")
    @Embedded
    val imperial: Imperial
)
