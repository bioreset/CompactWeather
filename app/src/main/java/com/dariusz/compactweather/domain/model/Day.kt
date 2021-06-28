package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.dariusz.compactweather.utils.WindUtils.windDirection

data class Day(
    @ColumnInfo(name = "dayweathercondition")
    val weatherCondition: String,
    @ColumnInfo(name = "daythunderstormprobability")
    val thunderstormProbability: Int,
    @ColumnInfo(name = "dayrainprobability")
    val rainProbability: Int,
    @ColumnInfo(name = "daysnowprobability")
    val snowProbability: Int,
    @ColumnInfo(name = "daywind")
    val wind: String,
    @ColumnInfo(name = "dayrain")
    val rain: String,
    @ColumnInfo(name = "daysnow")
    val snow: String,
    @ColumnInfo(name = "dayhoursofrain")
    val hoursOfRain: Double,
    @ColumnInfo(name = "dayhoursofsnow")
    val hoursOfSnow: Double,
    @ColumnInfo(name = "daycloudcover")
    val cloudCover: Int
) {
    companion object {
        fun dayToDB(input: DayJson): Day {
            return Day(
                weatherCondition = input.weatherCondition,
                thunderstormProbability = input.thunderstormProbability,
                rainProbability = input.rainProbability,
                snowProbability = input.snowProbability,
                wind = windDirection(input.wind.direction.degrees) + ", " + input.wind.speed.value.toString() + " " + input.wind.speed.unit,
                rain = input.rain.value.toString() + " " + input.rain.unit,
                snow = input.snow.value.toString() + " " + input.snow.unit,
                hoursOfRain = input.hoursOfRain,
                hoursOfSnow = input.hoursOfSnow,
                cloudCover = input.cloudCover
            )
        }
    }
}
