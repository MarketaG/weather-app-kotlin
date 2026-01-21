package com.marketagracova.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marketagracova.weatherapp.data.FavoriteCityRepository
import com.marketagracova.weatherapp.data.SearchHistoryManager

class WeatherViewModelFactory(
    private val repository: FavoriteCityRepository,
    private val searchHistoryManager: SearchHistoryManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository, searchHistoryManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}