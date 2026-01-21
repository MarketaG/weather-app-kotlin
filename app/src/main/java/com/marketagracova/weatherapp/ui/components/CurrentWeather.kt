package com.marketagracova.weatherapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marketagracova.weatherapp.data.Weather

@Composable
fun CurrentWeather(temp: Double, weather: List<Weather>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // weather icon
        AsyncImage(
            model = "https://openweathermap.org/img/wn/${weather[0].icon}@2x.png",
            contentDescription = "Weather icon",
            modifier = Modifier.size(160.dp)
        )

        // temperature
        Text(
            text = "${temp.toInt()}°",
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold
        )

        // description
        Text(
            text = weather[0].main,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSecondary

        )
    }
}