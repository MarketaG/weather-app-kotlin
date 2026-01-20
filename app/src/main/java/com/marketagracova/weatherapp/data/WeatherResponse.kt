package com.marketagracova.weatherapp.data

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val sys: Sys,
    val name: String
)

data class Main(
    val temp: Double,
    val humidity: Int,
    val pressure: Int
)

data class Weather(
    val description: String,
    val icon: String,
    val main: String  // "Clear", "Clouds", "Rain", ...
)

data class Wind(
    val speed: Double  // m/s
)

data class Sys(
    val sunrise: Long,  // Unix timestamp
    val sunset: Long    // Unix timestamp
)