package com.marketagracova.weatherapp.data

data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long,  // timestamp
    val main: Main,
    val weather: List<Weather>
)