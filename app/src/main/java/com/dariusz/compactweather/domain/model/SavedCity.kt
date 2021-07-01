package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "savedcities")
@JsonClass(generateAdapter = true)
data class SavedCity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "cityID")
    @field:Json(name = "Key")
    val cityID: Int,
    @ColumnInfo(name = "cityName")
    @field:Json(name = "LocalizedName")
    val cityName: String
)
