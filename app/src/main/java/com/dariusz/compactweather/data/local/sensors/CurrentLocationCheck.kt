package com.dariusz.compactweather.data.local.sensors

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.google.android.gms.location.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface CurrentLocationCheck {

    suspend fun getCurrentLocation(): Flow<CurrentLocation>

}

@ExperimentalCoroutinesApi
@SuppressLint("MissingPermission")
class CurrentLocationCheckImpl
@Inject constructor(
    private val context: Context
) : CurrentLocationCheck {

    override suspend fun getCurrentLocation(): Flow<CurrentLocation> {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        return fusedLocationProviderClient.getCurrentLocationNow()
    }

    private suspend fun FusedLocationProviderClient.getCurrentLocationNow(): Flow<CurrentLocation> =
        callbackFlow {
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
                        trySend(setLocationData(location)).isSuccess
                    }
                }
            }
            requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
            awaitClose {
                removeLocationUpdates(locationCallback)
            }
        }

    private fun setLocationData(location: Location) =
        CurrentLocation(
            longitude = location.longitude,
            latitude = location.latitude
        )
}