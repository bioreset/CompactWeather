package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Direction(
    @field:Json(name = "Degrees")
    @ColumnInfo(name = "directiondegrees")
    val degrees: Int
)
