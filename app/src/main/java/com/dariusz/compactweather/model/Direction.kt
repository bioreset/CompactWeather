package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Direction(
    @field:Json(name = "Degrees")
    val degrees: Int
)
