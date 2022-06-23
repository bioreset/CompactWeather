package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.location.LocationManager
import com.dariusz.compactweather.domain.model.GpsState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GPSStateCheck
@Inject
constructor(
    @ApplicationContext private val context: Context
) {

    val currentGpsStatus: Flow<GpsState> = flow { emit(context.gpsStatus()) }

    private fun Context.gpsStatus(): GpsState {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return checkGpsStatus(locationManager)
    }

    private fun checkGpsStatus(locationManager: LocationManager) = isGpsEnabled(
        locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER
        )
    )

    private fun isGpsEnabled(status: Boolean): GpsState {
        return if (status)
            GpsState(state = true)
        else
            GpsState(state = false)
    }
}