package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.sensors.GPSStateCheck
import com.dariusz.compactweather.data.local.sensors.NetworkStateCheck
import com.dariusz.compactweather.data.local.sensors.PermissionsCheck
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequirementsRepository
@Inject constructor(
    private val gpsStateCheck: GPSStateCheck,
    private val networkStateCheck: NetworkStateCheck,
    private val permissionsCheck: PermissionsCheck
) {

    fun checkGpsState() = gpsStateCheck.currentGpsStatus

    fun checkNetworkState() = networkStateCheck.currentNetworkStatus

    fun checkPermissionsState() = permissionsCheck.currentPermissionStatus

}