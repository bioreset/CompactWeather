package com.dariusz.compactweather.presentation.screens.dailyforecast

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.presentation.components.common.CenteredHeader
import com.dariusz.compactweather.presentation.components.common.ExpandableContents
import com.dariusz.compactweather.presentation.components.common.LeftRightText
import com.dariusz.compactweather.utils.DateTimeUtils.shortDate
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun DailyForecastScreen(
    cityKey: String,
    dailyForecastViewModel: DailyForecastViewModel = hiltViewModel()
) {

    val dailyForecast = dailyForecastViewModel.dailyForecast.collectAsState()

    dailyForecast.showOnScreen {
        DailyForecast(it)
    }

    LaunchedEffect(Unit) {
        dailyForecastViewModel.fetchDailyForecast(cityKey)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DailyForecast(dailyForecastList: List<DailyForecast>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        stickyHeader {
            CenteredHeader("Daily forecast")
        }
        items(dailyForecastList) {
            DailyCard(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DailyCard(dailyForecast: DailyForecast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ExpandableContents(
            initialContent = {
                MinimizedCard(dailyForecast)
            },
            fullContent = {
                FullCard(dailyForecast)
            }
        )
    }
}

@Composable
private fun MinimizedCard(dailyForecast: DailyForecast) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Text(
                text = dailyForecast.dateTime.shortDate(),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
private fun FullCard(item: DailyForecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        LeftRightText("Sun: ", item.sun)
        LeftRightText("Moon", item.moon)
        LeftRightText("Temperature", item.temperature)
        LeftRightText("Real Feel Temp: ", item.realFeelTemperature)
        LeftRightText("Hours of sun: ", item.hoursOfSun)
        Divider()
        ExpandableContents(
            initialContent = {
                Text(
                    text = "Day",
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            fullContent = {
                Column {
                    Text(
                        text = "Day",
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    item.dayJson.let {
                        LeftRightText("Weather condition: ", it.weatherCondition)
                        LeftRightText(
                            "Thunderstorm probability: ",
                            it.thunderstormProbability.toString()
                        )
                        LeftRightText("Rain probability: ", it.rainProbability.toString())
                        LeftRightText("Snow probability: ", it.snowProbability.toString())
                        LeftRightText("Wind: ", it.wind)
                        LeftRightText("Rain: ", it.rain)
                        LeftRightText("Snow: ", it.snow)
                        LeftRightText("Hours of rain: ", it.hoursOfRain.toString())
                        LeftRightText("Hours of snow: ", it.hoursOfSnow.toString())
                        LeftRightText("Cloud cover: ", it.cloudCover.toString())
                    }
                }
            }
        )
        Divider()
        ExpandableContents(
            initialContent = {
                Text(
                    text = "Night",
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            fullContent = {
                Column {
                    Text(
                        text = "Night",
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    item.nightJson.let {
                        LeftRightText("Weather condition: ", it.weatherCondition)
                        LeftRightText(
                            "Thunderstorm probability: ",
                            it.thunderstormProbability.toString()
                        )
                        LeftRightText("Rain probability: ", it.rainProbability.toString())
                        LeftRightText("Snow probability: ", it.snowProbability.toString())
                        LeftRightText("Wind: ", it.wind)
                        LeftRightText("Rain: ", it.rain)
                        LeftRightText("Snow: ", it.snow)
                        LeftRightText("Hours of rain: ", it.hoursOfRain.toString())
                        LeftRightText("Hours of snow: ", it.hoursOfSnow.toString())
                        LeftRightText("Cloud cover: ", it.cloudCover.toString())
                    }
                }
            }
        )
    }
}