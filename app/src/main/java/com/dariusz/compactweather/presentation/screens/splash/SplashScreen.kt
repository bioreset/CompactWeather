package com.dariusz.compactweather.presentation.screens.splash

import android.content.Context
import android.content.Intent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dariusz.compactweather.presentation.MainActivity
import com.dariusz.compactweather.presentation.components.common.LaunchButton
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun SplashScreen(splashScreenViewModel: SplashScreenViewModel = hiltViewModel()) {

    val currentContext = LocalContext.current

    val citiesState = splashScreenViewModel.cities.collectAsState()

    citiesState.showOnScreen { listOfCities ->
        if (listOfCities.isNotEmpty())
            listOfCities.forEach {
                LaunchButton(it.cityName) {
                    currentContext.startMainActivity()
                }
            }
        else
            Text("No cities found", color = MaterialTheme.colorScheme.onBackground)
    }
}

private fun Context.startMainActivity() = startActivity(Intent(this, MainActivity::class.java))