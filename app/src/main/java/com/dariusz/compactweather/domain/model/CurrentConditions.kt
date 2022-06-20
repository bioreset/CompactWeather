package com.dariusz.compactweather.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariusz.compactweather.utils.WindUtils.windDirection

@Entity(tableName = "currentconditions")
data class CurrentConditions(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "currentdatetime")
    val datetime: String,
    @ColumnInfo(name = "currentweathercondition")
    val weatherCondition: String,
    @ColumnInfo(name = "currenttemperature")
    val temperature: String,
    @ColumnInfo(name = "currentrealfeeltemperature")
    val realFeelTemperature: String,
    @ColumnInfo(name = "currenthumidity")
    val humidity: Int,
    @ColumnInfo(name = "currentwind")
    val wind: String,
    @ColumnInfo(name = "currentcloudcover")
    val cloudCover: Int,
    @ColumnInfo(name = "currentpressure")
    val pressure: String,
    @ColumnInfo(name = "currentmobilelink")
    val mobileLink: String
) {
    companion object {
        fun currentConditionsToDB(input: CurrentConditionsJson): CurrentConditions =
            CurrentConditions(
                datetime = input.datetime,
                weatherCondition = input.weatherCondition,
                temperature = input.temperature.metric.value.toString() + " " + input.temperature.metric.unit,
                realFeelTemperature = input.realFeelTemperature.metric.value.toString() + " " + input.realFeelTemperature.metric.unit,
                humidity = input.humidity,
                wind = windDirection(input.wind.direction.degrees) + ", " + input.wind.speed.metric.value.toString() + " " + input.wind.speed.metric.unit,
                cloudCover = input.cloudCover,
                pressure = input.pressure.metric.value.toString() + " " + input.pressure.metric.unit,
                mobileLink = input.mobileLink
            )
    }
}
