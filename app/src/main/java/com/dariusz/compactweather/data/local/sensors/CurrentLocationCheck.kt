package com.dariusz.compactweather.data.local.sensors

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.google.android.gms.location.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

interface CurrentLocationCheck {

    suspend fun getCurrentLocation(): CurrentLocation

}

@ExperimentalCoroutinesApi
@SuppressLint("MissingPermission")
class CurrentLocationCheckImpl
@Inject constructor(
    private val context: Context
) : CurrentLocationCheck {

    override suspend fun getCurrentLocation(): CurrentLocation {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        return fusedLocationProviderClient.getCurrentLocationNow()
    }

    private suspend fun FusedLocationProviderClient.getCurrentLocationNow(): CurrentLocation =
        suspendCancellableCoroutine { continuation ->
            val locationRequest: LocationRequest = LocationRequest.create()
                .apply {
                    interval = 1000L
                    fastestInterval = 500L
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult ?: return
                    for (location in locationResult.locations) {
                        continuation.resume(setLocationData(location))
                    }
                }
            }
            requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
            continuation.invokeOnCancellation {
                removeLocationUpdates(locationCallback)
            }
        }

    private fun setLocationData(location: Location) =
        CurrentLocation(
            longitude = location.longitude,
            latitude = location.latitude
        )
}