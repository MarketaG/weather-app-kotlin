package com.marketagracova.weatherapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    currentRoute: String?,
    navController: NavController
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == Screen.Home.route,
            onClick = { navController.navigate(Screen.Home.route) }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Forecast") },
            label = { Text("Forecast") },
            selected = currentRoute == Screen.Forecast.route,
            onClick = { navController.navigate(Screen.Forecast.route) }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = currentRoute == Screen.Favorites.route,
            onClick = { navController.navigate(Screen.Favorites.route) }
        )
    }
}