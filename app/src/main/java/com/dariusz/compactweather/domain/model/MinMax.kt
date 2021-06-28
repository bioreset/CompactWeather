package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Minimum(
    @field:Json(name = "Value")
    @ColumnInfo(name = "minvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "minunit")
    val unit: String
)

@JsonClass(generateAdapter = true)
data class Maximum(
    @field:Json(name = "Value")
    @ColumnInfo(name = "maxvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "maxunit")
    val unit: String
)