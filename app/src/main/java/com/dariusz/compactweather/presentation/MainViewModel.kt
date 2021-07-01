package com.dariusz.compactweather.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.di.SensorDataModule.provideCurrentLocationCheck
import com.dariusz.compactweather.di.SensorDataModule.provideGpsStateCheck
import com.dariusz.compactweather.di.SensorDataModule.provideNetworkStateCheck
import com.dariusz.compactweather.di.SensorDataModule.providePermissionStateCheck
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.GpsState
import com.dariusz.compactweather.domain.model.NetworkState
import com.dariusz.compactweather.domain.model.PermissionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MainViewModel
@Inject
constructor(
) : ViewModel() {

    private var _currentLocation = MutableStateFlow(CurrentLocation(0.0, 0.0))
    val currentLocation: StateFlow<CurrentLocation> = _currentLocation

    private var _networkState = MutableStateFlow(NetworkState())
    val networkState: StateFlow<NetworkState> = _networkState

    private val _gpsStatus = MutableStateFlow(GpsState())
    val gpsStatus: StateFlow<GpsState> = _gpsStatus

    private val _permissionsStatus = MutableStateFlow(PermissionsState())
    val permissionsStatus: StateFlow<PermissionsState> = _permissionsStatus

    fun getLocationData(context: Context) = viewModelScope.launch {
        provideCurrentLocationCheck(context)
            .getCurrentLocation()
            .collect {
                _currentLocation.value = it
            }
    }

    fun getNetworkState(context: Context) = viewModelScope.launch {
        provideNetworkStateCheck(context)
            .getNetworkStatus()
            .collect {
                _networkState.value = it
            }
    }

    fun getGpsState(context: Context) = viewModelScope.launch {
        provideGpsStateCheck(context)
            .getGPSStatus()
            .collect {
                _gpsStatus.value = it
            }
    }

    fun getPermissionState(context: Context) = viewModelScope.launch {
        providePermissionStateCheck(context)
            .getLivePermissionStatus()
            .collect {
                _permissionsStatus.value = it
            }
    }
}