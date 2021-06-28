package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Imperial(
    @field:Json(name = "Value")
    @ColumnInfo(name = "imperialvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "imperialunit")
    val unit: String
)