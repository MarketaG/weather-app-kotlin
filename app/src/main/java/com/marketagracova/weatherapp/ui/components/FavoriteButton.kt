package com.marketagracova.weatherapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                        weatherViewModel.removeFromFavoritesByName(currentCity)
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
            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Filled.Star,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.size(24.dp)
        )
    }
}