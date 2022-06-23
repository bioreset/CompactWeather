package com.dariusz.compactweather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.compactweather.domain.model.GpsState
import com.dariusz.compactweather.domain.model.NetworkState
import com.dariusz.compactweather.domain.model.PermissionsState
import com.dariusz.compactweather.domain.repository.RequirementsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    requirementsRepository: RequirementsRepository
) : ViewModel() {

    private val gpsStatus = requirementsRepository.checkGpsState()

    private val permissionsStatus = requirementsRepository.checkPermissionsState()

    private val wifiNetworkStatus = requirementsRepository.checkNetworkState()

    val requirementsStatus: StateFlow<RequirementsStatus> = combine(
        gpsStatus,
        permissionsStatus,
        wifiNetworkStatus
    ) { gps, perm, wifi ->
        RequirementsStatus(gps, perm, wifi)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RequirementsStatus()
    )

}

data class RequirementsStatus(
    val gpsStatusModel: GpsState = GpsState(),
    val permissionStatusModel: PermissionsState = PermissionsState(),
    val wifiStatusModel: NetworkState = NetworkState()
)