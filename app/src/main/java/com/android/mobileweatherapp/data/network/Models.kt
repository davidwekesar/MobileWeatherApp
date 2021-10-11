package com.android.mobileweatherapp.data.network

import com.squareup.moshi.Json

data class CityWeather(val weather: List<Weather>, val main: Main, val name: String)

data class Main(
    @Json(name = "temp")
    val currentTemp: Double,

    @Json(name = "temp_min")
    val minTemp: Double,

    @Json(name = "temp_max")
    val maxTemp: Double
)

data class Weather(val id: Int, val main: String)