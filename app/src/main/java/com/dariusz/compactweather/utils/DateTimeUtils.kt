package com.dariusz.compactweather.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateTime(datetime: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDate.parse(datetime, formatter).toString()
    }

}