package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rain(
    @field:Json(name = "Value")
    val value: Int,
    @field:Json(name = "Unit")
    val unit: String
)

@JsonClass(generateAdapter = true)
data class Snow(
    @field:Json(name = "Value")
    val value: Int,
    @field:Json(name = "Unit")
    val unit: String
)