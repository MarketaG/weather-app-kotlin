package com.marketagracova.weatherapp.viewmodel

import com.marketagracova.weatherapp.data.DayForecast
import com.marketagracova.weatherapp.data.ForecastItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

object ForecastProcessor {

    fun processForecast(items: List<ForecastItem>): List<DayForecast> {
        val grouped = items.groupBy { item ->
            formatDate(item.dt)
        }

        return grouped.map { (date, dayItems) ->
            val noonItem = dayItems.minByOrNull {
                abs(getHour(it.dt) - 12)
            } ?: dayItems.first()

            DayForecast(
                date = date,
                avgTemp = dayItems.map { it.main.temp }.average(),
                maxTemp = dayItems.maxByOrNull { it.main.temp }?.main?.temp ?: 0.0,
                minTemp = dayItems.minByOrNull { it.main.temp }?.main?.temp ?: 0.0,
                description = noonItem.weather[0].description,
                icon = noonItem.weather[0].icon
            )
        }.take(5) // jen 5 dní
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        return sdf.format(Date(timestamp * 1000))
    }

    private fun getHour(timestamp: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000
        return calendar.get(Calendar.HOUR_OF_DAY)
    }
}