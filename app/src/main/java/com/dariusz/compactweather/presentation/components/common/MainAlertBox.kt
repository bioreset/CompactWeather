package com.dariusz.compactweather.presentation.components.common

import android.content.Context
import android.content.Intent
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
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MainAlertBox(viewModel: MainViewModel, currentContext: Context) {

    viewModel.getNetworkState(currentContext)
    viewModel.getGpsState(currentContext)
    viewModel.getPermissionState(currentContext)

    val currentGPSStatus = viewModel.gpsStatus.collectAsState()
    val currentWifiStatus = viewModel.networkState.collectAsState()
    val currentPermissionsStatus = viewModel.permissionsStatus.collectAsState()

    if (currentGPSStatus.value.state == false) GpsAlert(currentContext)
    if (currentWifiStatus.value.state == false) WifiAlert(currentContext)
    if (currentPermissionsStatus.value.state == false) PermissionsAlert()
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
fun PermissionsAlert() {
    val openDialog = remember { mutableStateOf(true) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        openDialog.value = isGranted.containsValue(false)
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
                        launcher.launch(
                            mandatoryPermissions.toTypedArray()
                        )
                    }
                ) {
                    Text("Ok")
                }
            },
        )
    }
}