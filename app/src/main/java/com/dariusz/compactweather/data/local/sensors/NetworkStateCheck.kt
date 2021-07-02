package com.dariusz.compactweather.data.local.sensors

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import com.dariusz.compactweather.domain.model.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkStateCheck
@Inject
constructor(
    private val context: Context
) {

    fun getNetworkStatus() = context.networkStatus()

    private fun Context.networkStatus(): Flow<NetworkState> {
        val wifiManager = getSystemService(Context.WIFI_SERVICE) as? WifiManager
        val wifiStatus: NetworkState = getWifiStatus(wifiManager!!)
        return callbackFlow {
            val wifiScanReceiver = object : BroadcastReceiver() {
                override fun onReceive(c: Context, intent: Intent) {
                    if (intent.action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
                        unregisterReceiver(this)
                        trySend(wifiStatus).isSuccess
                    }
                }
            }
            registerReceiver(wifiScanReceiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
            awaitClose {
                unregisterReceiver(wifiScanReceiver)
            }
        }.stateIn(
            MainScope(),
            SharingStarted.WhileSubscribed(),
            NetworkState()
        )
    }

    private fun getWifiStatus(wifiManager: WifiManager) = isWifiEnabled(wifiManager.isWifiEnabled)

    private fun isWifiEnabled(status: Boolean): NetworkState {
        return if (status)
            NetworkState(state = true)
        else
            NetworkState(state = false)
    }
}