package com.dariusz.compactweather.presentation.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.dariusz.compactweather.domain.model.AppTheme
import com.dariusz.compactweather.presentation.components.navigation.MainNavigationHost
import com.dariusz.compactweather.presentation.components.theme.CompactWeatherTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun CWApp() {
    val theme by remember { mutableStateOf(AppTheme()) }
    val currentNavController = rememberNavController()
    CompactWeatherTheme(theme) {
        MainNavigationHost(currentNavController)
        MainAlertBox()
    }
}
