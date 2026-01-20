package com.marketagracova.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marketagracova.weatherapp.BuildConfig
import com.marketagracova.weatherapp.ui.components.*
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel

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

            WeatherHeader(cityName = data.name)

            Spacer(modifier = Modifier.height(24.dp))

            CurrentWeather(temp = data.main.temp, weather = data.weather)

            Spacer(modifier = Modifier.height(40.dp))

            WeatherDetails(
                windSpeed = data.wind.speed,
                humidity = data.main.humidity,
                sunrise = data.sys.sunrise,
                sunset = data.sys.sunset
            )

            Spacer(modifier = Modifier.height(32.dp))

            forecast?.let { forecastData ->
                ForecastList(forecastItems = forecastData.list)
            }
        } ?: run {
            CircularProgressIndicator()
        }
    }
}