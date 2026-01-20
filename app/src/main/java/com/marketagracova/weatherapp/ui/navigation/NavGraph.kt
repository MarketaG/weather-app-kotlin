package com.marketagracova.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marketagracova.weatherapp.ui.screens.FavoritesScreen
import com.marketagracova.weatherapp.ui.screens.ForecastScreen
import com.marketagracova.weatherapp.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Forecast.route) {
            ForecastScreen()
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }
    }
}