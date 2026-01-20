package com.marketagracova.weatherapp.data

data class GeoLocation(
    val name: String,
    val country: String,
    val state: String? = null,
    val lat: Double,
    val lon: Double
)