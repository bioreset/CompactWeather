package com.dariusz.compactweather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AutoComplete(
    @field:Json(name = "Key")
    val key: Int,
    @field:Json(name = "LocalizedName")
    val localizedName: String,
    @field:Json(name = "Country")
    val country: Country,
    @field:Json(name = "AdministrativeArea")
    val administrativeArea: AdministrativeArea
)