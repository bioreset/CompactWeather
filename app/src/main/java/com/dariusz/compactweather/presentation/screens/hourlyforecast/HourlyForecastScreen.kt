package com.dariusz.compactweather.presentation.screens.hourlyforecast

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.presentation.components.common.CenteredHeader
import com.dariusz.compactweather.presentation.components.common.ExpandableContents
import com.dariusz.compactweather.presentation.components.common.LeftRightText
import com.dariusz.compactweather.utils.DateTimeUtils.fullDate
import com.dariusz.compactweather.utils.DateTimeUtils.shortDate
import com.dariusz.compactweather.utils.DateTimeUtils.shortTime
import com.dariusz.compactweather.utils.ResultUtils.showOnScreen

@Composable
fun HourlyForecastScreen(
    cityKey: String,
    hourlyForecastViewModel: HourlyForecastViewModel = hiltViewModel()
) {

    val hourlyForecast = hourlyForecastViewModel.hourlyForecast.collectAsState()

    hourlyForecast.showOnScreen {
        HourlyForecast(it)
    }

    LaunchedEffect(Unit) {
        hourlyForecastViewModel.fetchHourlyForecast(cityKey)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HourlyForecast(hourlyForecastList: List<HourlyForecast>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        stickyHeader {
            CenteredHeader("Hourly forecast")
        }
        items(hourlyForecastList) {
            HourCard(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HourCard(hourForecast: HourlyForecast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ExpandableContents(
            initialContent = {
                MinimizedCard(hourForecast)
            },
            fullContent = {
                FullCard(hourForecast)
            }
        )
    }
}

@Composable
private fun MinimizedCard(hourForecast: HourlyForecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            LeftRightText(
                left = hourForecast.dateTime.fullDate(),
                right = hourForecast.weatherCondition
            )
        }
    }
}

@Composable
private fun FullCard(item: HourlyForecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.dateTime.shortDate() + " at " + item.dateTime.shortTime(),
                fontWeight = FontWeight(500)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        LeftRightText("Weather Condition: ", item.weatherCondition)
        LeftRightText("Temperature: ", item.temperature)
        LeftRightText("Real Feel Temp: ", item.realFeelTemperature)
        LeftRightText("Humidity: ", item.humidity.toString())
        LeftRightText("Wind: ", item.windV2)
        LeftRightText("Rain probability: ", item.rainProbability.toString())
        LeftRightText("Snow probability: ", item.snowProbability.toString())
        LeftRightText("Rain: ", item.rain)
        LeftRightText("Snow: ", item.snow)
        LeftRightText("Cloud cover: ", item.cloudCover.toString())
    }
}
