package com.marketagracova.weatherapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavBar(
    currentRoute: String?,
    navController: NavController,
    onSearchClick: () -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            indicatorColor = MaterialTheme.colorScheme.secondary,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondary
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = currentRoute == Screen.Home.route,
            onClick = { navController.navigate(Screen.Home.route) },
            colors = itemColors
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            selected = false,
            onClick = onSearchClick,
            colors = itemColors
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Forecast") },
            selected = currentRoute == Screen.Forecast.route,
            onClick = { navController.navigate(Screen.Forecast.route) },
            colors = itemColors
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            selected = currentRoute == Screen.Favorites.route,
            onClick = { navController.navigate(Screen.Favorites.route) },
            colors = itemColors
        )
    }
}
