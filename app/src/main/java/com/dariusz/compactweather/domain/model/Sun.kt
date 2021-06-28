package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sun(
    @field:Json(name = "Rise")
    @ColumnInfo(name = "sunrise")
    val riseTime: String,
    @field:Json(name = "Set")
    @ColumnInfo(name = "sunset")
    val setTime: String
)
