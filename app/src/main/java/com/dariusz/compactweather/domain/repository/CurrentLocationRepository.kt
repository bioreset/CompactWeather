package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.sensors.CurrentLocationCheck
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentLocationRepository
@Inject constructor(
    private val currentLocationCheck: CurrentLocationCheck
) {

    fun getCurrentLocation() = currentLocationCheck.getCurrentLocation()

}