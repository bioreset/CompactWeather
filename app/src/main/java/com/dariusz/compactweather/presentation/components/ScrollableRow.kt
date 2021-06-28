package com.dariusz.compactweather.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.presentation.theme.ThemeTypography
import com.dariusz.compactweather.presentation.theme.getTypography

@Composable
fun ScrollableHourlyForecast(hourlyForecast: List<HourlyForecast>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(hourlyForecast) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    "Weather Condition: " + item.weatherCondition,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Temperature: " + item.temperature,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Real Feel Temp: " + item.realFeelTemperature,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Humidity: " + item.humidity,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Wind: " + item.windV2,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Humidity: " + item.humidity,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Rain probability: " + item.rainProbability,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Snow probability: " + item.snowProbability,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Rain: " + item.rain,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Snow: " + item.snow,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Cloud cover: " + item.cloudCover,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Link: " + item.mobileLink,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Updates as of: " + item.dateTime,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
            }
        }
    }
}

@Composable
fun ScrollableDailyForecast(dailyForecast: List<DailyForecast>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(dailyForecast) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    "Sun: " + item.sun,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Moon" + item.moon,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Temperature" + item.temperature,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Real Feel Temp: " + item.realFeelTemperature,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Hours of sun: " + item.hoursOfSun,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Day: " + item.dayJson,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Night: " + item.nightJson,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Mobile Link: " + item.dateTime,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
                Text(
                    "Updated as of: " + item.dateTime,
                    modifier = Modifier.padding(12.dp),
                    style = ThemeTypography.Main.getTypography().body1
                )
            }
        }
    }
}