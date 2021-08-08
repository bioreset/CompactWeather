package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.location.LocationManager
import com.dariusz.compactweather.domain.model.GpsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

interface GPSStateCheck {

    val currentGpsStatus: GpsState

}

@ExperimentalCoroutinesApi
class GPSStateCheckImpl
@Inject
constructor(
    context: Context
) : GPSStateCheck {

    override val currentGpsStatus: GpsState =
        context.gpsStatus()

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