package com.marketagracova.weatherapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketagracova.weatherapp.data.ForecastItem
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ForecastList(forecastItems: List<ForecastItem>) {
    Column {
        Text(
            text = "Hourly Forecast",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(forecastItems.take(8)) { item ->
                ForecastCard(item)
            }
        }
    }
}

@Composable
fun ForecastCard(item: ForecastItem) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(170.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatForecastTime(item.dt),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            AsyncImage(
                model = "https://openweathermap.org/img/wn/${item.weather[0].icon}.png",
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${item.main.temp.toInt()}°",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
fun formatForecastTime(timestamp: Long): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(Date(timestamp * 1000))
}