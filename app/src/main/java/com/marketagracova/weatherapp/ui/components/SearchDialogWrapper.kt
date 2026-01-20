package com.marketagracova.weatherapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.marketagracova.weatherapp.BuildConfig
import com.marketagracova.weatherapp.ui.navigation.Screen
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel

@Composable
fun SearchDialogWrapper(
    onDismiss: () -> Unit,
    navController: NavController,
    weatherViewModel: WeatherViewModel
) {
    val searchResults by weatherViewModel.searchResults.observeAsState(emptyList())

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
        onDismiss = onDismiss
    )
}