package com.dariusz.compactweather.data.local.sensors

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import com.dariusz.compactweather.domain.model.GpsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GPSStateCheck
@Inject
constructor(
    private val context: Context
) {

    fun getGPSStatus() = context.gpsStatus()

    private fun Context.gpsStatus(): Flow<GpsState> {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsStatus: GpsState = checkGpsStatus(locationManager)
        return callbackFlow {
            val gpsSwitchStateReceiver = object : BroadcastReceiver() {
                override fun onReceive(c: Context, intent: Intent) {
                    if (intent.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
                        unregisterReceiver(this)
                        this@callbackFlow.trySend(gpsStatus).isSuccess
                    }
                }
            }
            registerReceiver(
                gpsSwitchStateReceiver,
                IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
            )
            awaitClose {
                unregisterReceiver(gpsSwitchStateReceiver)
            }
        }.stateIn(
            MainScope(),
            SharingStarted.WhileSubscribed(),
            GpsState()
        )
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