package com.dariusz.compactweather.presentation.components.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dariusz.compactweather.domain.model.GpsState
import com.dariusz.compactweather.domain.model.NetworkState
import com.dariusz.compactweather.domain.model.PermissionsState
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import kotlin.properties.Delegates

@Composable
fun MainAlertBox(
    onAllGranted: @Composable () -> Unit
) {
    val currentContext = LocalContext.current

    val viewModel: MainViewModel = hiltViewModel()

    val requirementsStatus by viewModel.requirementsStatus.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        ShowAlerts(
            context = currentContext,
            gpsStatusModel = requirementsStatus.gpsStatusModel,
            wifiStatusModel = requirementsStatus.wifiStatusModel,
            permissionStatusModel = requirementsStatus.permissionStatusModel,
            defaultView = {
                CenteredText("Grant all needed permissions")
            },
            onAllGranted = {
                onAllGranted()
            }
        )
    }
}

@Composable
fun ShowAlerts(
    context: Context,
    gpsStatusModel: GpsState,
    wifiStatusModel: NetworkState,
    permissionStatusModel: PermissionsState,
    defaultView: @Composable () -> Unit,
    onAllGranted: @Composable () -> Unit
) {
    if (gpsStatusModel.state == false)
        GpsAlert(context)

    if (wifiStatusModel.state == false)
        WifiAlert(context)

    if (permissionStatusModel.state == false)
        PermissionsAlert(context)

    if (gpsStatusModel.state == true &&
        wifiStatusModel.state == true &&
        permissionStatusModel.state == true
    ) onAllGranted()
    else
        defaultView()

}


@Composable
fun WifiAlert(currentContext: Context) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Wifi Required") },
            text = { Text("Turn on wifi") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        val intent = Intent()
                        intent.action = Settings.ACTION_WIFI_SETTINGS
                        currentContext.startActivity(intent)
                    }
                ) {
                    Text("Ok")
                }
            },
        )
    }
}

@Composable
fun GpsAlert(currentContext: Context) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Location Service is off") },
            text = { Text(text = "Turn on location") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        val intent = Intent()
                        intent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                        currentContext.startActivity(intent)
                    }
                ) {
                    Text("Ok")
                }
            },
        )
    }
}

@Composable
fun PermissionsAlert(currentContext: Context) {
    val permissionsState = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(true) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        openDialog.value = !isGranted.containsValue(true)
        permissionsState.value = isGranted.containsValue(true)
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Permissions required") },
            text = { Text(text = "Grant all required permissions") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        if (isPermissionGranted(currentContext) && permissionsState.value)
                            launcher.launch(mandatoryPermissions.toTypedArray())
                    }
                ) {
                    Text("Ok")
                }
            },
        )
    }
}

private fun isPermissionGranted(context: Context): Boolean {
    var result by Delegates.notNull<Boolean>()
    for (permission in mandatoryPermissions) {
        result = ActivityCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
    return result
}