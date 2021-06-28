package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metric(
    @field:Json(name = "Value")
    @ColumnInfo(name = "metricvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "metricunit")
    val unit: String
)
