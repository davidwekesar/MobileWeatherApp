package com.android.mobileweatherapp.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

enum class WeatherCondition { RAINY, CLOUDY, SUNNY }

fun tempToInt(temp: Double?): Int {
    return if (temp != null) {
        floor(temp).toInt()
    } else {
        0
    }
}

fun getWeatherName(weatherId: Int?): String {
    return if (weatherId != null) {
        when (getWeatherCondition(weatherId)) {
            WeatherCondition.RAINY -> {
                "Rainy"
            }
            WeatherCondition.CLOUDY -> {
                "Cloudy"
            }
            WeatherCondition.SUNNY -> {
                "Sunny"
            }
        }
    } else {
        ""
    }
}

fun getWeatherCondition(weatherId: Int): WeatherCondition {
    return when (weatherId) {
        in 200..622 -> {
            WeatherCondition.RAINY
        }
        in 701..781 -> {
            WeatherCondition.CLOUDY
        }
        in 801..804 -> {
            WeatherCondition.CLOUDY
        }
        else -> {
            WeatherCondition.SUNNY
        }
    }
}

fun convertUnixTimeToDay(time: Long): String {
    val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
    val date = Date(time * 1000)
    return sdf.format(date)
}