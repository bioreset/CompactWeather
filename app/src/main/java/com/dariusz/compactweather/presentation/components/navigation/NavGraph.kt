package com.dariusz.compactweather.presentation.components.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dariusz.compactweather.di.RepositoryModule.getDailyForecastRepository
import com.dariusz.compactweather.di.RepositoryModule.getHourlyForecastRepository
import com.dariusz.compactweather.di.RepositoryModule.getSavedCityRepository
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.screens.dailyforecast.DailyForecastScreen
import com.dariusz.compactweather.presentation.screens.dailyforecast.DailyForecastViewModel
import com.dariusz.compactweather.presentation.screens.dailyforecast.DailyForecastViewModelFactory
import com.dariusz.compactweather.presentation.screens.home.HomeScreen
import com.dariusz.compactweather.presentation.screens.home.HomeScreenViewModel
import com.dariusz.compactweather.presentation.screens.home.HomeScreenViewModelFactory
import com.dariusz.compactweather.presentation.screens.hourlyforecast.HourlyForecastScreen
import com.dariusz.compactweather.presentation.screens.hourlyforecast.HourlyForecastViewModel
import com.dariusz.compactweather.presentation.screens.hourlyforecast.HourlyForecastViewModelFactory
import com.dariusz.compactweather.presentation.screens.splash.SplashScreen
import com.dariusz.compactweather.presentation.screens.splash.SplashScreenViewModel
import com.dariusz.compactweather.presentation.screens.splash.SplashScreenViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@Composable
fun MainNavigationHost(navController: NavController, context: Context) {
    val mainViewModel: MainViewModel = viewModel()
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.SplashScreen.route
    ) {
        composable(Screens.AppScreens.SplashScreen.route) {
            val splashScreenViewModel: SplashScreenViewModel = viewModel(
                factory = SplashScreenViewModelFactory(
                    getSavedCityRepository(context)
                )
            )
            SplashScreen(splashScreenViewModel, mainViewModel, context, navController)
        }
        navigation(startDestination = Screens.AppScreens.HomeScreen.route, route = "Home") {
            composable(Screens.AppScreens.HomeScreen.route) {
                val homeScreenViewModel: HomeScreenViewModel = viewModel(
                    factory = HomeScreenViewModelFactory(
                        getSavedCityRepository(context)
                    )
                )
                HomeScreen(homeScreenViewModel, navController)
            }
            composable(Screens.AppScreens.DailyForecastScreen.route) {
                val dailyForecastScreenViewModel: DailyForecastViewModel = viewModel(
                    factory = DailyForecastViewModelFactory(
                        getDailyForecastRepository(context)
                    )

                )
                DailyForecastScreen(
                    dailyForecastScreenViewModel
                )
            }
            composable(Screens.AppScreens.HourlyForecastScreen.route) {
                val hourlyForecastScreenViewModel: HourlyForecastViewModel = viewModel(
                    factory = HourlyForecastViewModelFactory(
                        getHourlyForecastRepository(context)
                    )

                )
                HourlyForecastScreen(
                    hourlyForecastScreenViewModel
                )
            }
        }
    }
}
