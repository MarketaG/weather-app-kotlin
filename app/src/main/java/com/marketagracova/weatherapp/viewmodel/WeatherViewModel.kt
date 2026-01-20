package com.marketagracova.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marketagracova.weatherapp.data.RetrofitInstance
import com.marketagracova.weatherapp.data.WeatherResponse
import kotlinx.coroutines.launch
import android.util.Log
import com.marketagracova.weatherapp.data.ForecastResponse
import com.marketagracova.weatherapp.data.GeoLocation
import com.marketagracova.weatherapp.data.DayForecast


class WeatherViewModel : ViewModel() {
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> = _weather

    private val _currentCity = MutableLiveData("Prague")
    val currentCity: LiveData<String> = _currentCity

    private val _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse> = _forecast

    private val _searchResults = MutableLiveData<List<GeoLocation>>()
    val searchResults: LiveData<List<GeoLocation>> = _searchResults

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _dailyForecast = MutableLiveData<List<DayForecast>>()
    val dailyForecast: LiveData<List<DayForecast>> = _dailyForecast

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(city, apiKey)
                _weather.value = response
                _currentCity.value = city
                _errorMessage.value = null
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error: ${e.message}", e)
                _errorMessage.value = "Unable to load weather for $city"
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun setCity(city: String) {
        _currentCity.value = city
    }


    fun fetchForecast(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getForecast(city, apiKey)
                _forecast.value = response
                _dailyForecast.value = ForecastProcessor.processForecast(response.list)
                _currentCity.value = city
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Forecast error: ${e.message}", e)
            }
        }
    }

    fun searchCities(query: String, apiKey: String) {
        if (query.length < 2) {
            _searchResults.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                val results = RetrofitInstance.api.searchCities(query, 5, apiKey)
                _searchResults.value = results
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Search error: ${e.message}", e)
                _searchResults.value = emptyList()
            }
        }
    }
}
