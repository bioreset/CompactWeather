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
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.dariusz.compactweather.di.RepositoryModule.provideCurrentLocationRepository
import com.dariusz.compactweather.di.RepositoryModule.provideRequirementsRepository
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import com.dariusz.compactweather.utils.DataStateUtils.ManageDataStateOnScreen
import com.dariusz.compactweather.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.properties.Delegates

@ExperimentalCoroutinesApi
@Composable
fun MainAlertBox() {

    val currentContext = LocalContext.current

    val mainViewModel = composeViewModel {
        MainViewModel(
            provideCurrentLocationRepository(currentContext),
            provideRequirementsRepository(currentContext)
        )
    }

    val currentGPSStatus by remember(mainViewModel) {
        mainViewModel.gpsStatus
    }.collectAsState()

    val currentWifiStatus by remember(mainViewModel) {
        mainViewModel.networkState
    }.collectAsState()

    val currentPermissionsStatus by remember(mainViewModel) {
        mainViewModel.permissionsStatus
    }.collectAsState()

    ManageDataStateOnScreen(currentGPSStatus) {
        if (it.state == false) GpsAlert(currentContext)
    }

    ManageDataStateOnScreen(currentWifiStatus) {
        if (it.state == false) WifiAlert(currentContext)
    }

    ManageDataStateOnScreen(currentPermissionsStatus) {
        if (it.state == false) PermissionsAlert(currentContext)
    }

    LaunchedEffect(Unit) {
        mainViewModel.apply {
            getGpsState()
            getNetworkState()
            getPermissionState()
        }
    }

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