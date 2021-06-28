package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import com.dariusz.compactweather.domain.model.NetworkState

class NetworkStateCheck(
    private val context: Context
) {

    fun getNetworkStatus() = networkStatus()

    private fun networkStatus(): NetworkState {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val currentNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(currentNetwork)!!
        val wifiManager: WifiManager =
            context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    wifiManager.isWifiEnabled
            -> NetworkState(
                permissionState = true, connectivityState = true
            )
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    !wifiManager.isWifiEnabled
            -> NetworkState(
                permissionState = true, connectivityState = false
            )
            !networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    !networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    !networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    wifiManager.isWifiEnabled
            -> NetworkState(
                permissionState = false, connectivityState = true
            )
            else -> NetworkState(
                permissionState = false, connectivityState = false
            )
        }


    }
}