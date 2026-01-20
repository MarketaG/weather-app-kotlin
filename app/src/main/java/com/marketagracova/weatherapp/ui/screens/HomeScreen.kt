package com.marketagracova.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marketagracova.weatherapp.BuildConfig
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import com.marketagracova.weatherapp.data.ForecastItem


@Composable
fun HomeScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val weather by viewModel.weather.observeAsState()
    val forecast by viewModel.forecast.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Prague", BuildConfig.API_KEY)
        viewModel.fetchForecast("Prague", BuildConfig.API_KEY)

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        weather?.let { data ->
            Spacer(modifier = Modifier.height(40.dp))

            // locations with an icon
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = data.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // date with icon
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = getCurrentDate(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // weather icon from OpenWeather
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png",
                contentDescription = "Weather icon",
                modifier = Modifier.size(100.dp)
            )

            // temperature
            Text(
                text = "${data.main.temp.toInt()}°",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold
            )

            // description
            Text(
                text = data.weather[0].main,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(40.dp))

            // detail
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDetail("Wind", "${data.wind.speed} m/s")
                WeatherDetail("Humidity", "${data.main.humidity}%")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDetail("Sunrise", formatTime(data.sys.sunrise))
                WeatherDetail("Sunset", formatTime(data.sys.sunset))
            }
        } ?: run {
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Forecast seznam
        forecast?.let { forecastData ->
            Text(
                text = "Hourly Forecast",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(forecastData.list.take(8)) { item ->
                    ForecastCard(item)
                }
            }
        }
    }
}

@Composable
fun WeatherDetail(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}

fun formatTime(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}

fun getCurrentDate(): String {
    val format = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
    return format.format(Date())
}

@Composable
fun ForecastCard(item: ForecastItem) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatForecastTime(item.dt),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${item.weather[0].icon}.png",
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${item.main.temp.toInt()}°",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun formatForecastTime(timestamp: Long): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(Date(timestamp * 1000))
}