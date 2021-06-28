package com.dariusz.compactweather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @field:Json(name = "ID")
    val id: String,
    @field:Json(name = "LocalizedName")
    val localizedName: String
)