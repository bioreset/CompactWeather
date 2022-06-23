package com.dariusz.compactweather.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    fun String.fullDate(): String {
        val dateToParse = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        return formatter.format(dateToParse) ?: ""
    }

    fun String.shortDate(): String {
        val dateToParse = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return formatter.format(dateToParse) ?: ""
    }

    fun String.shortTime(): String {
        val dateToParse = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return formatter.format(dateToParse) ?: ""
    }

}