package com.marketagracova.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marketagracova.weatherapp.BuildConfig
import com.marketagracova.weatherapp.ui.components.*
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import com.marketagracova.weatherapp.ui.components.FavoriteButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import com.marketagracova.weatherapp.ui.components.AppLogo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weather by weatherViewModel.weather.observeAsState()
    val forecast by weatherViewModel.forecast.observeAsState()
    val currentCity by weatherViewModel.currentCity.observeAsState("Prague")

    val errorMessage by weatherViewModel.errorMessage.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            weatherViewModel.clearError()
        }
    }

    LaunchedEffect(currentCity) {
        weatherViewModel.fetchWeather(currentCity, BuildConfig.API_KEY)
        weatherViewModel.fetchForecast(currentCity, BuildConfig.API_KEY)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { AppLogo() },
                actions = {
                    FavoriteButton(
                        weather = weather,
                        currentCity = currentCity,
                        weatherViewModel = weatherViewModel
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
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
}