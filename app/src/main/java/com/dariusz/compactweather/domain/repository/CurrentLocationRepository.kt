package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.sensors.CurrentLocationCheck
import com.dariusz.compactweather.domain.model.CurrentLocation
import javax.inject.Inject

interface CurrentLocationRepository {

    suspend fun getCurrentLocation(): CurrentLocation

}

class CurrentLocationRepositoryImpl
@Inject constructor(
    private val currentLocationCheck: CurrentLocationCheck
) : CurrentLocationRepository {

    override suspend fun getCurrentLocation() = currentLocationCheck.getCurrentLocation()

}