package com.dariusz.compactweather.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    fun parseDate(date: String): String {
        val dateToParse = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        return formatter.format(dateToParse)
    }

}