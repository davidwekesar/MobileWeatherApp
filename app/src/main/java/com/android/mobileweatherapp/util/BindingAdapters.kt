package com.android.mobileweatherapp.util

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.android.mobileweatherapp.R
import kotlin.math.floor

@BindingAdapter("android:text")
fun setText(text: TextView, temp: Double) {
    val tempIntValue = tempToInt(temp)
    val formattedTemp = "$tempIntValue\u00B0"
    text.text = formattedTemp
}

@BindingAdapter("textWeather")
fun setWeatherName(textView: TextView, weatherId: Int) {
    val weatherName: String = getWeatherName(weatherId)
    textView.text = weatherName
}

@BindingAdapter("android:background")
fun setBackgroundColor(view: View, weatherId: Int) {
    val color = when(weatherId) {
        in 200..531 -> R.color.rainy
        in 801..804 -> R.color.cloudy
        800 -> R.color.sunny
        else -> R.color.sunny
    }
    view.background = ContextCompat.getDrawable(view.rootView.context, color)
}

fun tempToInt(temp: Double?): Int {
    return if (temp != null) {
        floor(temp).toInt()
    } else {
        0
    }
}

fun getWeatherName(weatherId: Int?): String {
    return if (weatherId != null) {
        when (weatherId) {
            in 200..531 -> {
                "Rain"
            }
            in 801..804 -> {
                "Cloudy"
            }
            800 -> {
                "Sunny"
            }
            else -> {
                ""
            }
        }
    } else {
        ""
    }
}