package com.marketagracova.weatherapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marketagracova.weatherapp.data.WeatherResponse
import com.marketagracova.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteButton(
    weather: WeatherResponse?,
    currentCity: String,
    weatherViewModel: WeatherViewModel = viewModel()
) {
    var isFavorite by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(currentCity) {
        isFavorite = weatherViewModel.isFavorite(currentCity)
    }

    IconButton(
        onClick = {
            weather?.let { data ->
                scope.launch {
                    if (isFavorite) {
                        // TODO: remove from favorites - need to find the FavoriteCity object
                        isFavorite = false
                    } else {
                        weatherViewModel.addToFavorites(
                            cityName = data.name,
                            country = data.sys.country ?: "",
                            lat = 0.0,
                            lon = 0.0
                        )
                        isFavorite = true
                    }
                }
            }
        }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }
}