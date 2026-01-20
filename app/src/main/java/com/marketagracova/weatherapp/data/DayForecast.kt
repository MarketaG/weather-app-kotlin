package com.marketagracova.weatherapp.data

data class DayForecast(
    val date: String,
    val avgTemp: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val description: String,
    val icon: String
)