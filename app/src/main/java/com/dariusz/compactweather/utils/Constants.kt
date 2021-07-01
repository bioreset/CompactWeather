package com.dariusz.compactweather.utils

import android.Manifest

object Constants {

    //API SETUP
    const val API_URL = "https://dataservice.accuweather.com/"
    const val API_KEY = ""

    //DB SETUP
    const val DB_NAME = "compact_weather_db"
    const val DB_VERSION = 1

    const val CITY_KEY_FOR_TESTS = "273125"

    val mandatoryPermissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.CHANGE_WIFI_STATE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.INTERNET
    )
}