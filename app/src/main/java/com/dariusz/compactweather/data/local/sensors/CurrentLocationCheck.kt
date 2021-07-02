package com.dariusz.compactweather.data.local.sensors

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.google.android.gms.location.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("MissingPermission")
class CurrentLocationCheck
@Inject constructor(
    private val context: Context
) {

    suspend fun getCurrentLocation(): Flow<CurrentLocation> {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        return fusedLocationProviderClient.getCurrentLocationAsFlow()

    }

    private suspend fun FusedLocationProviderClient.getCurrentLocationAsFlow(): Flow<CurrentLocation> =
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
                        this@callbackFlow.trySend(setLocationData(location)).isSuccess
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
        }.stateIn(
            MainScope(),
            SharingStarted.WhileSubscribed(),
            CurrentLocation(0.0, 0.0)
        )

    private fun setLocationData(location: Location) =
        CurrentLocation(
            longitude = location.longitude,
            latitude = location.latitude
        )
}