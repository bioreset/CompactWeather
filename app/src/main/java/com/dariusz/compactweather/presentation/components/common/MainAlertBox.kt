package com.dariusz.compactweather.presentation.components.common

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.properties.Delegates

@ExperimentalCoroutinesApi
@Composable
fun MainAlertBox(mainViewModel: MainViewModel = viewModel(), currentContext: Context) {

    mainViewModel.getNetworkState(currentContext)
    mainViewModel.getGpsState(currentContext)
    mainViewModel.getPermissionState(currentContext)

    val currentGPSStatus = mainViewModel.gpsStatus.collectAsState()
    val currentWifiStatus = mainViewModel.networkState.collectAsState()
    val currentPermissionsStatus = mainViewModel.permissionsStatus.collectAsState()

    if (currentGPSStatus.value.state == false) GpsAlert(currentContext)
    if (currentWifiStatus.value.state == false) WifiAlert(currentContext)
    if (currentPermissionsStatus.value.state == false) PermissionsAlert(currentContext)
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