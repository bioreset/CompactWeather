package com.dariusz.compactweather.data.local.sensors

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SuppressLint("MissingPermission")
class CurrentLocationCheck
@Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getCurrentLocation(): Flow<CurrentLocation> {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        return fusedLocationProviderClient.getCurrentLocationNow()
    }

    private fun FusedLocationProviderClient.getCurrentLocationNow(): Flow<CurrentLocation> =
        callbackFlow {
            val locationRequest: LocationRequest = LocationRequest.create()
                .apply {
                    interval = 1000L
                    fastestInterval = 500L
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    for (location in p0.locations) {
                        trySend(setLocationData(location))
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