package com.dariusz.compactweather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecastResponse(
    @field:Json(name = "DailyForecasts")
    val value: List<DailyForecastJson>
)