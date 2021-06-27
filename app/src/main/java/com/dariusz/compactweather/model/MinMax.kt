package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Minimum(
    @field:Json(name = "Value")
    val value: Double,
    @field:Json(name = "Unit")
    val unit: String
)

@JsonClass(generateAdapter = true)
data class Maximum(
    @field:Json(name = "Value")
    val value: Double,
    @field:Json(name = "Unit")
    val unit: String
)