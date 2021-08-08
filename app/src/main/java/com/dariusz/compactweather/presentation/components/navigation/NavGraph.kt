package com.dariusz.compactweather.presentation.components.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.compactweather.di.RepositoryModule.provideCurrentConditionsRepository
import com.dariusz.compactweather.di.RepositoryModule.provideDailyForecastRepository
import com.dariusz.compactweather.di.RepositoryModule.provideHourlyForecastRepository
import com.dariusz.compactweather.di.RepositoryModule.provideSavedCityRepository
import com.dariusz.compactweather.presentation.MainViewModel
import com.dariusz.compactweather.presentation.screens.dailyforecast.DailyForecastScreen
import com.dariusz.compactweather.presentation.screens.dailyforecast.DailyForecastViewModel
import com.dariusz.compactweather.presentation.screens.home.HomeScreen
import com.dariusz.compactweather.presentation.screens.home.HomeScreenViewModel
import com.dariusz.compactweather.presentation.screens.hourlyforecast.HourlyForecastScreen
import com.dariusz.compactweather.presentation.screens.hourlyforecast.HourlyForecastViewModel
import com.dariusz.compactweather.utils.ViewModelUtils.composeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MainNavigationHost(
    navController: NavController,
    context: Context,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.AppScreens.HomeScreen.route
    ) {
        composable(route = Screens.AppScreens.HomeScreen.route) {
            val homeScreenViewModel = composeViewModel {
                HomeScreenViewModel(
                    provideCurrentConditionsRepository(context),
                    provideSavedCityRepository(context)
                )
            }
            HomeScreen(homeScreenViewModel, mainViewModel, navController)
        }
        composable(route = Screens.AppScreens.DailyForecastScreen.route.plus("/{city_key}")) {
            val dailyForecastScreenViewModel = composeViewModel {
                DailyForecastViewModel(
                    provideDailyForecastRepository(context)
                )
            }
            DailyForecastScreen(
                it.arguments?.getString("city_key") ?: "",
                dailyForecastScreenViewModel
            )
        }
        composable(route = Screens.AppScreens.HourlyForecastScreen.route.plus("/{city_key}")) {
            val hourlyForecastScreenViewModel = composeViewModel {
                HourlyForecastViewModel(
                    provideHourlyForecastRepository(context)
                )
            }
            HourlyForecastScreen(
                it.arguments?.getString("city_key") ?: "",
                hourlyForecastScreenViewModel
            )
        }
    }
}


