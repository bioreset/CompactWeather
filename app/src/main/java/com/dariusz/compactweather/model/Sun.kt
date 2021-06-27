package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sun(
    @field:Json(name = "Rise")
    val riseTime: String,
    @field:Json(name = "Set")
    val setTime: String
)
