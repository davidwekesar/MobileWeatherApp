package com.android.mobileweatherapp.data.network

import com.squareup.moshi.Json

data class CityWeather(
    @Json(name = "current")
    val currentWeather: CurrentWeather,

    @Json(name = "daily")
    val dailyForecast: List<DailyForecast>
)

data class CurrentWeather(
    @Json(name = "temp")
    val currentTemp: Double,

    val weather: List<Weather>
)

data class DailyForecast(
    @Json(name = "dt")
    val time: Long,

    @Json(name = "temp")
    val temperatures: Temperatures,

    val weather: List<Weather>
)

data class Temperatures(
    @Json(name = "day")
    val dayTemp: Double,

    @Json(name = "min")
    val minTemp: Double,

    @Json(name = "max")
    val maxTemp: Double
)

data class Weather(
    val id: Int,

    @Json(name = "main")
    val name: String
)