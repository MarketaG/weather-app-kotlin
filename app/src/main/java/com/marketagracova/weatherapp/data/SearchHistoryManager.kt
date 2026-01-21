package com.marketagracova.weatherapp.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchHistoryManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("search_history", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val KEY_HISTORY = "search_history"
        private const val MAX_HISTORY = 5
    }

    fun addToHistory(cityName: String, country: String) {
        val history = getHistory().toMutableList()

        // remove duplicates
        history.removeAll { it.first == cityName }

        // add to top
        history.add(0, Pair(cityName, country))

        // limit to a maximum of 5 items
        if (history.size > MAX_HISTORY) {
            history.removeAt(history.size - 1)
        }

        // save
        val json = gson.toJson(history)
        prefs.edit().putString(KEY_HISTORY, json).apply()
    }

    fun getHistory(): List<Pair<String, String>> {
        val json = prefs.getString(KEY_HISTORY, null) ?: return emptyList()
        val type = object : TypeToken<List<Pair<String, String>>>() {}.type
        return gson.fromJson(json, type)
    }

    fun clearHistory() {
        prefs.edit().remove(KEY_HISTORY).apply()
    }
}