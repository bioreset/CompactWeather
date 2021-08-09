package com.dariusz.compactweather.presentation

import androidx.lifecycle.ViewModel
import com.dariusz.compactweather.domain.model.*
import com.dariusz.compactweather.domain.repository.CurrentLocationRepository
import com.dariusz.compactweather.domain.repository.RequirementsRepository
import com.dariusz.compactweather.utils.ViewModelUtils.collectLatestState
import com.dariusz.compactweather.utils.ViewModelUtils.launchVMTask
import com.dariusz.compactweather.utils.ViewModelUtils.manageResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MainViewModel
@Inject
constructor(
    private val currentLocationRepository: CurrentLocationRepository,
    private val requirementsRepository: RequirementsRepository
) : ViewModel() {

    private var _currentLocation = MutableStateFlow<DataState<CurrentLocation>>(DataState.Idle)
    val currentLocation: StateFlow<DataState<CurrentLocation>> = _currentLocation

    private var _networkState = MutableStateFlow<DataState<NetworkState>>(DataState.Idle)
    val networkState: StateFlow<DataState<NetworkState>> = _networkState

    private val _gpsStatus = MutableStateFlow<DataState<GpsState>>(DataState.Idle)
    val gpsStatus: StateFlow<DataState<GpsState>> = _gpsStatus

    private val _permissionsStatus = MutableStateFlow<DataState<PermissionsState>>(DataState.Idle)
    val permissionsStatus: StateFlow<DataState<PermissionsState>> = _permissionsStatus

    @InternalCoroutinesApi
    fun getLocationData() = launchVMTask {
        currentLocationRepository
            .getCurrentLocation()
            .collectLatestState(_currentLocation)
    }

    fun getNetworkState() = launchVMTask {
        manageResult(
            _networkState,
            requirementsRepository.checkNetworkState()
        )
    }

    fun getGpsState() = launchVMTask {
        manageResult(
            _gpsStatus,
            requirementsRepository.checkGpsState()
        )
    }

    fun getPermissionState() = launchVMTask {
        manageResult(
            _permissionsStatus,
            requirementsRepository.checkPermissionsState()
        )
    }
}