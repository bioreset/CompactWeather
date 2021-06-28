package com.dariusz.compactweather.domain.model

data class NetworkState (
    val permissionState: Boolean? = false,
    val connectivityState: Boolean? = false
) {
    companion object {
        fun validateNetworkState(networkState: NetworkState): Boolean {
            return networkState == NetworkState(permissionState = true, connectivityState = true)
        }
    }
}