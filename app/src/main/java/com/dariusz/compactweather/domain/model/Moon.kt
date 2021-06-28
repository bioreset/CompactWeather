package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Moon(
    @field:Json(name = "Rise")
    @ColumnInfo(name = "moonrise")
    val riseTime: String,
    @field:Json(name = "Set")
    @ColumnInfo(name = "moonset")
    val setTime: String,
    @field:Json(name = "Phase")
    @ColumnInfo(name = "moonphase")
    val phase: String
)
