package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariusz.compactweather.utils.WindUtils.windDirection

@Entity(tableName = "hourlyforecast")
data class HourlyForecast(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "hourlydatetime")
    val dateTime: String,
    @ColumnInfo(name = "hourlyweathercondition")
    val weatherCondition: String,
    @ColumnInfo(name = "hourlytemperature")
    val temperature: String,
    @ColumnInfo(name = "hourlyrealfeeltemperature")
    val realFeelTemperature: String,
    @ColumnInfo(name = "hourlywind")
    val windV2: String,
    @ColumnInfo(name = "hourlyhumidity")
    val humidity: Int,
    @ColumnInfo(name = "hourlyrainprobability")
    val rainProbability: Int,
    @ColumnInfo(name = "hourlysnowprobability")
    val snowProbability: Int,
    @ColumnInfo(name = "hourlyrain")
    val rain: String,
    @ColumnInfo(name = "hourlysnow")
    val snow: String,
    @ColumnInfo(name = "hourlycloudcover")
    val cloudCover: Int,
    @ColumnInfo(name = "hourlymobilelink")
    val mobileLink: String
) {
    companion object {
        fun hourlyForecastsToDB(input: List<HourlyForecastJson>): List<HourlyForecast> {
            return input.map { jsonInput ->
                HourlyForecast(
                    dateTime = jsonInput.dateTime,
                    weatherCondition = jsonInput.weatherCondition,
                    temperature = jsonInput.temperature.value.toString() + " " + jsonInput.temperature.unit,
                    realFeelTemperature = jsonInput.realFeelTemperature.value.toString() + " " + jsonInput.realFeelTemperature.unit,
                    windV2 = windDirection(jsonInput.windV2.direction.degrees) + ", " + jsonInput.windV2.speed.value.toString() + " " + jsonInput.windV2.speed.unit,
                    humidity = jsonInput.humidity,
                    rainProbability = jsonInput.rainProbability,
                    snowProbability = jsonInput.snowProbability,
                    rain = jsonInput.rain.value.toString() + " " + jsonInput.rain.unit,
                    snow = jsonInput.snow.value.toString() + " " + jsonInput.snow.unit,
                    cloudCover = jsonInput.cloudCover,
                    mobileLink = jsonInput.mobileLink
                )
            }
        }
    }
}