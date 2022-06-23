package com.dariusz.compactweather.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.presentation.components.common.LeftRightText
import com.dariusz.compactweather.presentation.components.navigation.Screens
import com.dariusz.compactweather.utils.DateTimeUtils.fullDate
import com.dariusz.compactweather.utils.NavigationUtils.navigateToWithArguments
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val homeScreenData = homeScreenViewModel.homeScreenData.collectAsState()

    homeScreenData.showOnScreen {
        CurrentConditions(
            navController = navController,
            savedCity = it.savedCity,
            input = it.currentConditions
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrentConditions(
    navController: NavController,
    savedCity: SavedCity,
    input: CurrentConditions
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            )
            {
                Text(
                    text = "Current Conditions in ${savedCity.cityName}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                LeftRightText(left = "Date: ", right = input.datetime.fullDate())
                LeftRightText(left = "Weather condition: ", right = input.weatherCondition)
                LeftRightText(left = "Temperature: ", right = input.temperature)
                LeftRightText(left = "Real feel temp: ", right = input.realFeelTemperature)
                LeftRightText(left = "Humidity: ", right = input.humidity.toString())
                LeftRightText(left = "Wind: ", right = input.wind)
                LeftRightText(left = "Cloud cover: ", right = input.cloudCover.toString())
                LeftRightText(left = "Pressure: ", right = input.pressure)
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Next 12h",
                        modifier = Modifier
                            .clickable {
                                navController.navigateToWithArguments(
                                    Screens.AppScreens.HourlyForecastScreen.route,
                                    savedCity.cityID.toString()
                                )
                            },
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight(600)),
                    )
                    Text(
                        text = "Next 5d",
                        modifier = Modifier
                            .clickable {
                                navController.navigateToWithArguments(
                                    Screens.AppScreens.DailyForecastScreen.route,
                                    savedCity.cityID.toString()
                                )
                            },
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight(600))
                    )
                }
            }
        }
    }
}