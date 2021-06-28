package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import com.dariusz.compactweather.utils.WindUtils.windDirection

data class Night(
    @ColumnInfo(name = "nightweathercondition")
    val weatherCondition: String,
    @ColumnInfo(name = "nightthunderstormprobability")
    val thunderstormProbability: Int,
    @ColumnInfo(name = "nightrainprobability")
    val rainProbability: Int,
    @ColumnInfo(name = "nightsnowprobability")
    val snowProbability: Int,
    @ColumnInfo(name = "nightwind")
    val wind: String,
    @ColumnInfo(name = "nightrain")
    val rain: String,
    @ColumnInfo(name = "nightsnow")
    val snow: String,
    @ColumnInfo(name = "nighthoursofrain")
    val hoursOfRain: Double,
    @ColumnInfo(name = "nighthoursofsnow")
    val hoursOfSnow: Double,
    @ColumnInfo(name = "nightcloudcover")
    val cloudCover: Int
) {
    companion object {
        fun nightToDB(input: NightJson): Night {
            return Night(
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
