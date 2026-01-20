package com.marketagracova.weatherapp.data

import kotlinx.coroutines.flow.Flow

class FavoriteCityRepository(private val dao: FavoriteCityDao) {

    val allFavorites: Flow<List<FavoriteCity>> = dao.getAllFavorites()

    suspend fun addFavorite(city: FavoriteCity) {
        dao.insertFavorite(city)
    }

    suspend fun removeFavorite(city: FavoriteCity) {
        dao.deleteFavorite(city)
    }

    suspend fun isFavorite(cityName: String): Boolean {
        return dao.isFavorite(cityName)
    }
}