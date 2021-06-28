package com.dariusz.compactweather.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import com.dariusz.compactweather.domain.model.AppTheme
import com.dariusz.compactweather.presentation.navigation.MainNavigationHost
import com.dariusz.compactweather.presentation.navigation.NavigatorButton
import com.dariusz.compactweather.presentation.navigation.Screens
import com.dariusz.compactweather.presentation.theme.CompactWeatherTheme
import com.dariusz.compactweather.presentation.theme.ThemeSaver

@Composable
fun CWApp() {
    val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
    val navHostController = rememberNavController()
    CompactWeatherTheme(theme) {
        MainNavigationHost(navHostController)
        NavigatorButton(navHostController, screens = Screens.AppScreens.DetailsScreen)
    }
}
