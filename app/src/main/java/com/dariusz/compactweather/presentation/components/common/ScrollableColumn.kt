package com.dariusz.compactweather.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.utils.DateTimeUtils.parseDate
import com.dariusz.compactweather.utils.MobileLinkUtils.openLinkInBrowser

@Composable
fun ScrollableHourlyForecast(hourlyForecast: List<HourlyForecast>) {
    val currentContext = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(hourlyForecast) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(12.dp)
            ) {
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
                LeftRightText("Updates as of: ", parseDate(item.dateTime))

                Button(onClick = {
                    openLinkInBrowser(currentContext, item.mobileLink)
                }) {
                    Text("Open in browser")
                }
            }
        }
    }
}

@Composable
fun ScrollableDailyForecast(dailyForecast: List<DailyForecast>) {
    val currentContext = LocalContext.current
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(dailyForecast) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                LeftRightText("Sun: ", item.sun)
                LeftRightText("Moon", item.moon)
                LeftRightText("Temperature", item.temperature)
                LeftRightText("Real Feel Temp: ", item.realFeelTemperature)
                LeftRightText("Hours of sun: ", item.hoursOfSun)

                Text("Day")
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

                Text("Night")
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

                LeftRightText("Updated as of: ", parseDate(item.dateTime))

                Button(onClick = {
                    openLinkInBrowser(currentContext, item.mobileLink)
                }) {
                    Text("Open in browser")
                }
            }
        }
    }
}