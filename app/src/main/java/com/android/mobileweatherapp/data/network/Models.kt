package com.android.mobileweatherapp.data.network

import com.squareup.moshi.Json

data class CityWeather(
    val weather: List<Weather>,

    @Json(name = "main")
    val temperatures: Temperatures,

    @Json(name = "name")
    val cityName: String
)

data class Temperatures(
    @Json(name = "temp")
    val currentTemp: Double,

    @Json(name = "temp_min")
    val minTemp: Double,

    @Json(name = "temp_max")
    val maxTemp: Double
)

data class Weather(
    val id: Int,

    @Json(name = "main")
    val name: String
)