package com.dariusz.compactweather.presentation.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.presentation.components.navigation.Screens
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.presentation.components.theme.getTypography
import com.dariusz.compactweather.utils.NavigationUtils

@Composable
fun CurrentConditionsPresentation(
    navController: NavController,
    savedCitiesList: List<SavedCity>,
    input: CurrentConditions
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(savedCitiesList) {
            Text(
                text = "Current Conditions in your location: ",
                style = ThemeTypography.Main.getTypography().h5
            )
            LeftRightText(left = "Date: ", right = input.datetime)
            LeftRightText(left = "Weather condition: ", right = input.weatherCondition)
            LeftRightText(left = "Temperature: ", right = input.temperature)
            LeftRightText(left = "Real feel temp: ", right = input.realFeelTemperature)
            LeftRightText(left = "Humidity: ", right = input.humidity.toString())
            LeftRightText(left = "Wind: ", right = input.wind)
            LeftRightText(left = "Cloud cover: ", right = input.cloudCover.toString())
            LeftRightText(left = "Pressure: ", right = input.pressure)
            Text(text = "Updates as of ${input.datetime}")
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
                        text = it.cityName,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        NavigationUtils.navigateToWithArguments(
                            navController,
                            Screens.AppScreens.HourlyForecastScreen.route,
                            it.cityID.toString()
                        )
                    }) {
                        Text("12h Forecast")
                    }
                    Button(onClick = {
                        NavigationUtils.navigateToWithArguments(
                            navController,
                            Screens.AppScreens.DailyForecastScreen.route,
                            it.cityID.toString()
                        )
                    }) {
                        Text("5d Forecast")
                    }
                }
            }
        }
    }
}

@Composable
fun LeftRightText(left: String, right: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ConstraintLayout {
            val (text1, text2) = createRefs()
            Text(left, modifier = Modifier.constrainAs(text1) {
                centerVerticallyTo(parent)
                linkTo(parent.start, parent.end, bias = 0.2f)
            }
            )
            Text(right, modifier = Modifier.constrainAs(text2) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
                linkTo(parent.start, parent.end, bias = 0.8f)
            }
            )
        }
    }
}

