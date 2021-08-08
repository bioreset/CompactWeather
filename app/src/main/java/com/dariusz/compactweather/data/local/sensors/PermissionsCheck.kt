package com.dariusz.compactweather.data.local.sensors

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.dariusz.compactweather.domain.model.PermissionsState
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import javax.inject.Inject

interface PermissionsCheck {

    val currentPermissionStatus: PermissionsState

}

class PermissionsCheckImpl
@Inject
constructor(
    context: Context
) : PermissionsCheck {

    override val currentPermissionStatus: PermissionsState =
        context.livePermissionsStatus()

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