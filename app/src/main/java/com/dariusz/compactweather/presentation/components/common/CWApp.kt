package com.dariusz.compactweather.presentation.components.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dariusz.compactweather.domain.model.AppTheme
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.components.navigation.MainNavigationHost
import com.dariusz.compactweather.presentation.components.theme.CompactWeatherTheme
import com.dariusz.compactweather.presentation.components.theme.ThemeSaver
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@Composable
fun CWApp() {
    val theme by rememberSaveable(stateSaver = ThemeSaver) { mutableStateOf(AppTheme()) }
    val currentNavController = rememberNavController()
    val currentContext = LocalContext.current
    val mainViewModel: MainViewModel = viewModel()
    CompactWeatherTheme(theme) {
        MainNavigationHost(currentNavController, currentContext)
        MainAlertBox(mainViewModel, currentContext)
    }
}
