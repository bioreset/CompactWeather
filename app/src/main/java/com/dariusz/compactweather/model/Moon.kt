package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Moon(
    @field:Json(name = "Rise")
    val riseTime: String,
    @field:Json(name = "Set")
    val setTime: String,
    @field:Json(name = "Phase")
    val phase: String
)
