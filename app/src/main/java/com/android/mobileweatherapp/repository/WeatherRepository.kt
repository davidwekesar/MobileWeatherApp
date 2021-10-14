package com.android.mobileweatherapp.repository

import com.android.mobileweatherapp.data.network.OpenWeatherMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {

    private val openWeatherMapService = OpenWeatherMap.openWeatherMapService

    suspend fun getWeatherData() = openWeatherMapService.getWeatherData()
}