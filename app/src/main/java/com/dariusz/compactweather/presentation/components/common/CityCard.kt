package com.dariusz.compactweather.presentation.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.presentation.components.navigation.Screens
import com.dariusz.compactweather.presentation.components.navigation.navigateTo

@Composable
fun CityCardList(navController: NavController, savedCitiesList: List<SavedCity>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(savedCitiesList) {
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillParentMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.cityName,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        navigateTo(
                            navController,
                            Screens.AppScreens.HourlyForecastScreen.route
                        )
                    }) {
                        Text("12h Forecast")
                    }
                    Button(onClick = {
                        navigateTo(
                            navController,
                            Screens.AppScreens.DailyForecastScreen.route
                        )
                    }) {
                        Text("5d Forecast")
                    }
                }
            }
        }
    }
}

