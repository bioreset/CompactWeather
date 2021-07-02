package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.dariusz.compactweather.domain.model.PermissionsState
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class PermissionsCheck
@Inject
constructor(
    private val context: Context
) {

    fun getLivePermissionStatus() =
        context.livePermissionsStatus()

    private fun Context.livePermissionsStatus(): Flow<PermissionsState> {
        val permissionStatus =
            handlePermissionCheck(checkPermissions(applicationContext, mandatoryPermissions))
        return flow {
            emit(permissionStatus)
        }.stateIn(
            MainScope(),
            SharingStarted.WhileSubscribed(),
            PermissionsState()
        )
    }

    private fun handlePermissionCheck(status: Boolean): PermissionsState {
        return if (status)
            PermissionsState(state = true)
        else
            PermissionsState(state = false)
    }

    private fun checkPermissions(context: Context, permissionsToListen: List<String>): Boolean {
        return permissionsToListen.all {
            ActivityCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}