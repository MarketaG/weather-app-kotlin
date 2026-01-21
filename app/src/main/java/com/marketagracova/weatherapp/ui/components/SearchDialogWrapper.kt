package com.marketagracova.weatherapp.ui.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.marketagracova.weatherapp.BuildConfig
import com.marketagracova.weatherapp.data.LocationProvider
import com.marketagracova.weatherapp.ui.navigation.Screen
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchDialogWrapper(
    onDismiss: () -> Unit,
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {
    val searchResults by weatherViewModel.searchResults.observeAsState(emptyList())
    val context = LocalContext.current
    val locationProvider = remember { LocationProvider(context) }
    val scope = rememberCoroutineScope()

    // permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            // permission granted - fetch location
            scope.launch {
                val location = locationProvider.getCurrentLocation()
                location?.let { (lat, lon) ->
                    weatherViewModel.fetchWeatherByLocation(lat, lon, BuildConfig.API_KEY)
                    weatherViewModel.fetchForecastByLocation(lat, lon, BuildConfig.API_KEY)
                    navController.navigate(Screen.Home.route)
                    onDismiss()
                }
            }
        }
    }

    SearchDialog(
        searchResults = searchResults,
        onSearch = { query ->
            weatherViewModel.searchCities(query, BuildConfig.API_KEY)
        },
        onCitySelected = { city ->
            weatherViewModel.setCity(city.name)
            navController.navigate(Screen.Home.route)
            onDismiss()
        },
        onCurrentLocationClick = {
            // Request permissions
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        },
        onDismiss = onDismiss
    )
}