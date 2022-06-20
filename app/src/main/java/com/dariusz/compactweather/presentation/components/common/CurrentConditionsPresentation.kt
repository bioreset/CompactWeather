package com.dariusz.compactweather.presentation.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.presentation.components.navigation.Screens
import com.dariusz.compactweather.utils.DateTimeUtils
import com.dariusz.compactweather.utils.NavigationUtils.navigateToWithArguments

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentConditionsPresentation(
    navController: NavController,
    savedCity: SavedCity,
    input: CurrentConditions
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        item {
            Text(
                text = "Current Conditions in ${savedCity.cityName}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(3.dp))
            LeftRightText(left = "Date: ", right = input.datetime)
            LeftRightText(left = "Weather condition: ", right = input.weatherCondition)
            LeftRightText(left = "Temperature: ", right = input.temperature)
            LeftRightText(left = "Real feel temp: ", right = input.realFeelTemperature)
            LeftRightText(left = "Humidity: ", right = input.humidity.toString())
            LeftRightText(left = "Wind: ", right = input.wind)
            LeftRightText(left = "Cloud cover: ", right = input.cloudCover.toString())
            LeftRightText(left = "Pressure: ", right = input.pressure)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Updates as of ${DateTimeUtils.parseDate(input.datetime)}")
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = savedCity.cityName,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        navController.navigateToWithArguments(
                            Screens.AppScreens.HourlyForecastScreen.route,
                            savedCity.cityID.toString()
                        )
                    }) {
                        Text("12h Forecast")
                    }
                    Button(onClick = {
                        navController.navigateToWithArguments(
                            Screens.AppScreens.DailyForecastScreen.route,
                            savedCity.cityID.toString()
                        )
                    }) {
                        Text("5d Forecast")
                    }
                }
            }
        }
    }
}