package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rain(
    @field:Json(name = "Value")
    @ColumnInfo(name = "rainvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "rainunit")
    val unit: String
)

@JsonClass(generateAdapter = true)
data class Snow(
    @field:Json(name = "Value")
    @ColumnInfo(name = "snowvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "snowunit")
    val unit: String
)