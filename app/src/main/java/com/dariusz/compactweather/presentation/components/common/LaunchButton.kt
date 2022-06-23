package com.dariusz.compactweather.presentation.components.common

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
import androidx.compose.ui.unit.dp

@Composable
fun LaunchButton(city: String, action: () -> Unit) {
    val currentCity = remember { mutableStateOf(city) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            OutlinedButton(
                onClick = { action() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Check weather in ${currentCity.value}",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )
}
