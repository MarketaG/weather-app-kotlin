package com.marketagracova.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marketagracova.weatherapp.ui.screens.FavoritesScreen
import com.marketagracova.weatherapp.ui.screens.ForecastScreen
import com.marketagracova.weatherapp.ui.screens.HomeScreen
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(weatherViewModel = weatherViewModel)
        }

        composable(Screen.Forecast.route) {
            ForecastScreen(weatherViewModel = weatherViewModel)
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                weatherViewModel = weatherViewModel,
                navController = navController
            )
        }
    }
}