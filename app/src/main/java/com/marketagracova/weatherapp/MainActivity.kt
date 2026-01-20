package com.marketagracova.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marketagracova.weatherapp.ui.components.SearchDialogWrapper
import com.marketagracova.weatherapp.ui.navigation.BottomNavBar
import com.marketagracova.weatherapp.ui.navigation.NavGraph
import com.marketagracova.weatherapp.ui.theme.WeatherAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel
import com.marketagracova.weatherapp.data.WeatherDatabase
import com.marketagracova.weatherapp.data.FavoriteCityRepository
import com.marketagracova.weatherapp.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create a database and repository
        val database = WeatherDatabase.getDatabase(applicationContext)
        val repository = FavoriteCityRepository(database.favoriteCityDao())
        val factory = WeatherViewModelFactory(repository)

        setContent {
            WeatherAppTheme {
                val weatherViewModel: WeatherViewModel = viewModel(factory = factory)
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                var showSearchDialog by remember { mutableStateOf(false) }

                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentRoute = currentRoute,
                            navController = navController,
                            onSearchClick = { showSearchDialog = true }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(
                            navController = navController,
                            weatherViewModel = weatherViewModel
                        )

                        if (showSearchDialog) {
                            SearchDialogWrapper(
                                onDismiss = { showSearchDialog = false },
                                navController = navController,
                                weatherViewModel = weatherViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}