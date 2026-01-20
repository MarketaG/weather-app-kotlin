package com.marketagracova.weatherapp.ui.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Forecast : Screen("forecast", "Forecast")
    object Favorites : Screen("favorites", "Favorites")
}