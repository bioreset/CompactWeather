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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

interface NetworkStateCheck {

    val currentNetworkStatus: Flow<NetworkState>

}

@ExperimentalCoroutinesApi
class NetworkStateCheckImpl
@Inject
constructor(
    context: Context
) : NetworkStateCheck {

    override val currentNetworkStatus: Flow<NetworkState> =
        context.networkStatus().shareIn(MainScope(), SharingStarted.WhileSubscribed())

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
        }
    }

    private fun getWifiStatus(wifiManager: WifiManager) = isWifiEnabled(wifiManager.isWifiEnabled)

    private fun isWifiEnabled(status: Boolean): NetworkState {
        return if (status)
            NetworkState(state = true)
        else
            NetworkState(state = false)
    }
}