package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariusz.compactweather.domain.model.Day.Companion.dayToDB
import com.dariusz.compactweather.domain.model.Night.Companion.nightToDB

@Entity(tableName = "dailyforecast")
data class DailyForecast(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "dailydatetime")
    val dateTime: String,
    @ColumnInfo(name = "dailysun")
    val sun: String,
    @ColumnInfo(name = "dailymoon")
    val moon: String,
    @ColumnInfo(name = "dailytemperature")
    val temperature: String,
    @ColumnInfo(name = "dailyrealfeeltemperature")
    val realFeelTemperature: String,
    @ColumnInfo(name = "dailyhoursofsun")
    val hoursOfSun: String,
    @Embedded
    val dayJson: Day,
    @Embedded
    val nightJson: Night,
    @ColumnInfo(name = "dailymobilelink")
    val mobileLink: String
) {
    companion object {
        fun dailyForecastsToDB(input: List<DailyForecastJson>): List<DailyForecast> {
            return input.map { jsonInput ->
                DailyForecast(
                    dateTime = jsonInput.dateTime,
                    sun = jsonInput.sun.riseTime + " / " + jsonInput.sun.setTime,
                    moon = jsonInput.moon.riseTime + " / " + jsonInput.moon.setTime + " / " + jsonInput.moon.phase,
                    temperature =
                    jsonInput.temperature.minimum.value.toString() + " " + jsonInput.temperature.minimum.unit
                            + " - " + jsonInput.temperature.maximum.value + " " + jsonInput.temperature.maximum.unit,
                    realFeelTemperature = jsonInput.realFeelTemperature.minimum.value.toString() + " " + jsonInput.realFeelTemperature.minimum.unit
                            + " - " + jsonInput.realFeelTemperature.maximum.value + " " + jsonInput.realFeelTemperature.maximum.unit,
                    hoursOfSun = jsonInput.hoursOfSun.toString(),
                    dayJson = dayToDB(jsonInput.dayJson),
                    nightJson = nightToDB(jsonInput.nightJson),
                    mobileLink = jsonInput.mobileLink
                )
            }
        }
    }
}
