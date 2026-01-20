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


class WeatherViewModel : ViewModel() {
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> = _weather

    private val _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse> = _forecast

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(city, apiKey)
                _weather.value = response
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error: ${e.message}", e)
            }
        }
    }

    fun fetchForecast(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getForecast(city, apiKey)
                _forecast.value = response
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Forecast error: ${e.message}", e)
            }
        }
    }
}
