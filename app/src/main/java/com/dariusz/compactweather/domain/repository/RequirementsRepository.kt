package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.sensors.GPSStateCheck
import com.dariusz.compactweather.data.local.sensors.NetworkStateCheck
import com.dariusz.compactweather.data.local.sensors.PermissionsCheck
import com.dariusz.compactweather.domain.model.GpsState
import com.dariusz.compactweather.domain.model.NetworkState
import com.dariusz.compactweather.domain.model.PermissionsState
import javax.inject.Inject

interface RequirementsRepository {

    suspend fun checkGpsState(): GpsState

    suspend fun checkNetworkState(): NetworkState

    suspend fun checkPermissionsState(): PermissionsState

}

class RequirementsRepositoryImpl
@Inject constructor(
    private val gpsStateCheck: GPSStateCheck,
    private val networkStateCheck: NetworkStateCheck,
    private val permissionsCheck: PermissionsCheck
) : RequirementsRepository {


    override suspend fun checkGpsState() = gpsStateCheck.currentGpsStatus

    override suspend fun checkNetworkState() = networkStateCheck.currentNetworkStatus

    override suspend fun checkPermissionsState() = permissionsCheck.currentPermissionStatus

}