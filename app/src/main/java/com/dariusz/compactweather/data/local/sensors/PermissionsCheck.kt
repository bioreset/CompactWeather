package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.dariusz.compactweather.domain.model.PermissionsState
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionsCheck
@Inject
constructor(
    @ApplicationContext private val context: Context
) {

    val currentPermissionStatus: Flow<PermissionsState> =
        flow { emit(context.livePermissionsStatus()) }

    private fun Context.livePermissionsStatus(): PermissionsState =
        handlePermissionCheck(checkPermissions(applicationContext, mandatoryPermissions))

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