package com.dariusz.compactweather.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.dariusz.compactweather.model.AppTheme
import com.dariusz.compactweather.ui.theme.CompactWeatherTheme
import com.dariusz.compactweather.ui.theme.ThemeSaver

@Composable
fun CWApp() {
    val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
    CompactWeatherTheme(theme) {
        Greeting(name = "App")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}