package com.dariusz.compactweather.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.dariusz.compactweather.domain.model.AppTheme
import com.dariusz.compactweather.presentation.screens.detail.DetailScreen
import com.dariusz.compactweather.presentation.theme.CompactWeatherTheme
import com.dariusz.compactweather.presentation.theme.ThemeSaver

@Composable
fun CWApp() {
    val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
    CompactWeatherTheme(theme) {
        DetailScreen()
    }
}
