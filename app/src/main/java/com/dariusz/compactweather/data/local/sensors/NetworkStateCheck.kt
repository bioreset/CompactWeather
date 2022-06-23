package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.net.wifi.WifiManager
import com.dariusz.compactweather.domain.model.NetworkState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStateCheck
@Inject
constructor(
    @ApplicationContext private val context: Context
) {

    val currentNetworkStatus: Flow<NetworkState> = flow { emit(context.networkStatus()) }

    private fun Context.networkStatus(): NetworkState {
        val wifiManager = getSystemService(Context.WIFI_SERVICE) as? WifiManager
        return getWifiStatus(wifiManager!!)
    }

    private fun getWifiStatus(wifiManager: WifiManager) = isWifiEnabled(wifiManager.isWifiEnabled)

    private fun isWifiEnabled(status: Boolean): NetworkState {
        return if (status)
            NetworkState(state = true)
        else
            NetworkState(state = false)
    }
}