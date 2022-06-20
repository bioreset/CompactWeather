package com.dariusz.compactweather.presentation.components.common

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.dariusz.compactweather.utils.Constants.mandatoryPermissions
import kotlin.properties.Delegates

@Composable
fun LaunchButton(city: String, action: () -> Unit) {
    val currentContext = LocalContext.current
    val permissionsState = remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        permissionsState.value = isGranted.containsValue(true)
    }
    val currentCity = remember { city.ifEmpty { "your location" } }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            OutlinedButton(
                onClick = {
                    when (isPermissionGranted(currentContext) && permissionsState.value) {
                        true -> {
                            action()
                        }
                        else -> {
                            launcher.launch(mandatoryPermissions.toTypedArray())
                        }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Check weather in $currentCity",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )

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

