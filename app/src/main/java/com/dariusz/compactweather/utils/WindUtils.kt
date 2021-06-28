package com.dariusz.compactweather.utils

object WindUtils {

    fun windDirection(degrees: Int): String {
        return when (degrees) {
            in 0..23 -> "N"
            in 24..67 -> "NE"
            in 68..113 -> "E"
            in 114..157 -> "SE"
            in 158..203 -> "S"
            in 204..247 -> "SW"
            in 248..293 -> "E"
            in 294..337 -> "NE"
            in 338..360 -> "N"
            else -> "?"
        }
    }

}