package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.net.wifi.WifiManager
import com.dariusz.compactweather.domain.model.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

interface NetworkStateCheck {

    val currentNetworkStatus: NetworkState

}

@ExperimentalCoroutinesApi
class NetworkStateCheckImpl
@Inject
constructor(
    context: Context
) : NetworkStateCheck {

    override val currentNetworkStatus: NetworkState =
        context.networkStatus()

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