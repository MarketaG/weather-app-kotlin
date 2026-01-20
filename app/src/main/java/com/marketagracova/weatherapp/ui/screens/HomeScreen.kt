package com.marketagracova.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel

@Composable
fun HomeScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val weather by viewModel.weather.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            viewModel.fetchWeather("Prague", com.marketagracova.weatherapp.BuildConfig.API_KEY)
        }) {
            Text("Get weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        weather?.let {
            Text("City: ${it.name}")
            Text("Temperature: ${it.main.temp}°C")
            Text("Humidity: ${it.main.humidity}%")
            Text("Description: ${it.weather[0].description}")
        }
    }
}