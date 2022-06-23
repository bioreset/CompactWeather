package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariusz.compactweather.domain.model.Day.Companion.dayToDB
import com.dariusz.compactweather.domain.model.Night.Companion.nightToDB
import com.dariusz.compactweather.utils.DateTimeUtils.shortTime

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
                    sun = "from " + jsonInput.sun.riseTime.shortTime() + " to " + jsonInput.sun.setTime.shortTime(),
                    moon = "from " + jsonInput.moon.riseTime.shortTime() + " to " + jsonInput.moon.setTime.shortTime(),
                    temperature = "from " + jsonInput.temperature.minimum.value.toString() + " " + jsonInput.temperature.minimum.unit
                            + " to " + jsonInput.temperature.maximum.value + " " + jsonInput.temperature.maximum.unit,
                    realFeelTemperature = "from " + jsonInput.realFeelTemperature.minimum.value.toString() + " " + jsonInput.realFeelTemperature.minimum.unit
                            + " to " + jsonInput.realFeelTemperature.maximum.value + " " + jsonInput.realFeelTemperature.maximum.unit,
                    hoursOfSun = jsonInput.hoursOfSun.toString(),
                    dayJson = dayToDB(jsonInput.dayJson),
                    nightJson = nightToDB(jsonInput.nightJson),
                    mobileLink = jsonInput.mobileLink
                )
            }
        }
    }
}
