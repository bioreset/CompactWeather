package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @field:Json(name = "Direction")
    @Embedded
    val direction: Direction,
    @field:Json(name = "Speed")
    @Embedded
    val speed: Speed
)

@JsonClass(generateAdapter = true)
data class WindV2(
    @field:Json(name = "Direction")
    @Embedded
    val direction: Direction,
    @field:Json(name = "Speed")
    @Embedded
    val speed: SpeedV2
)

@JsonClass(generateAdapter = true)
data class Speed(
    @field:Json(name = "Metric")
    @Embedded
    val metric: Metric,
    @field:Json(name = "Imperial")
    @Embedded
    val imperial: Imperial
)

@JsonClass(generateAdapter = true)
data class SpeedV2(
    @field:Json(name = "Value")
    @ColumnInfo(name = "windspeedvalue")
    val value: Double,
    @field:Json(name = "Unit")
    @ColumnInfo(name = "windspeedunit")
    val unit: String
)