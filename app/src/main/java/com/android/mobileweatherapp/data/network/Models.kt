package com.android.mobileweatherapp.data.network

import com.squareup.moshi.Json

data class CityWeather(
    val weather: List<Weather>,

    @Json(name = "main")
    val temperatures: Temperatures,

    @Json(name = "name")
    val cityName: String
)

data class Forecast(val list: List<DayForecast>)

data class DayForecast(
    @Json(name = "dt")
    val time: Long,

    @Json(name = "main")
    val temperatures: Temperatures,

    val weather: List<Weather>
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