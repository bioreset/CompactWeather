package com.dariusz.compactweather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dariusz.compactweather.model.DailyForecast
import com.dariusz.compactweather.model.HourlyForecast

@Composable
fun ScrollableHourlyForecast(hourlyForecast: List<HourlyForecast>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(hourlyForecast) { item ->
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "Timestamp: " + item.dateTime,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                )
                Text(
                    "Weather condition: " + item.weatherCondition,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}

@Composable
fun ScrollableDailyForecast(hourlyForecast: List<DailyForecast>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(hourlyForecast) { item ->
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "Timestamp: " + item.dateTime,
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                )
                Text(
                    "Min. temp: " + item.temperature.minimum.value.toString() + " C",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}