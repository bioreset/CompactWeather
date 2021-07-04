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
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MainViewModel
@Inject
constructor(
) : ViewModel() {

    private var _currentLocation = MutableSharedFlow<CurrentLocation>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val currentLocation: SharedFlow<CurrentLocation> = _currentLocation
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)

    private var _networkState = MutableStateFlow(NetworkState())
    val networkState: StateFlow<NetworkState> = _networkState

    private val _gpsStatus = MutableStateFlow(GpsState())
    val gpsStatus: StateFlow<GpsState> = _gpsStatus

    private val _permissionsStatus = MutableStateFlow(PermissionsState())
    val permissionsStatus: StateFlow<PermissionsState> = _permissionsStatus

    fun getLocationData(context: Context) = viewModelScope.launch {
        _currentLocation.emitAll(provideCurrentLocationCheck(context).getCurrentLocation())
    }

    fun getNetworkState(context: Context) = viewModelScope.launch {
        provideNetworkStateCheck(context)
            .currentNetworkStatus
            .collect {
                _networkState.value = it
            }
    }

    fun getGpsState(context: Context) = viewModelScope.launch {
        provideGpsStateCheck(context)
            .currentGpsStatus
            .collect {
                _gpsStatus.value = it
            }
    }

    fun getPermissionState(context: Context) = viewModelScope.launch {
        providePermissionStateCheck(context)
            .currentPermissionStatus
            .collect {
                _permissionsStatus.value = it
            }
    }
}