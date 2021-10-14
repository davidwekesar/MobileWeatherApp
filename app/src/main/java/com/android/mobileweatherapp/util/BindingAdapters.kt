package com.android.mobileweatherapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.mobileweatherapp.R
import com.android.mobileweatherapp.data.network.DailyForecast
import com.android.mobileweatherapp.ui.adapters.ForecastAdapter
import com.android.mobileweatherapp.viewmodels.OpenWeatherApiStatus

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
    val color = when (getWeatherCondition(weatherId)) {
        WeatherCondition.RAINY -> R.color.rainy
        WeatherCondition.CLOUDY -> R.color.cloudy
        WeatherCondition.SUNNY -> R.color.sunny
    }
    view.background = ContextCompat.getDrawable(view.rootView.context, color)
}

@BindingAdapter("android:src")
fun setDrawable(imageView: ImageView, weatherId: Int) {
    val drawable = when (getWeatherCondition(weatherId)) {
        WeatherCondition.RAINY -> R.drawable.forest_rainy
        WeatherCondition.CLOUDY -> R.drawable.forest_cloudy
        WeatherCondition.SUNNY -> R.drawable.forest_sunny
    }
    imageView.setImageResource(drawable)
}

@BindingAdapter("addList")
fun bindRecyclerview(recyclerView: RecyclerView, list: List<DailyForecast>?) {
    list?.let {
        val forecastAdapter = ForecastAdapter(list)
        recyclerView.adapter = forecastAdapter
    }
}

@BindingAdapter("weatherIcon")
fun setWeatherIcon(imageView: ImageView, weatherId: Int) {
    val weatherIcon = when (getWeatherCondition(weatherId)) {
        WeatherCondition.RAINY -> R.drawable.rain
        WeatherCondition.CLOUDY -> R.drawable.partlysunny
        WeatherCondition.SUNNY -> R.drawable.clear
    }
    imageView.setImageResource(weatherIcon)
}

@BindingAdapter("android:text")
fun setDayOfWeek(textView: TextView, time: Long) {
    textView.text = convertUnixTimeToDay(time)
}

@BindingAdapter("android:visibility")
fun setErrorVisibility(view: View, openWeatherApiStatus: OpenWeatherApiStatus?) {
    openWeatherApiStatus?.let {
        when (openWeatherApiStatus) {
            OpenWeatherApiStatus.LOADING -> {
                view.visibility = View.INVISIBLE
            }
            OpenWeatherApiStatus.DONE -> {
                view.visibility = View.INVISIBLE
            }
            OpenWeatherApiStatus.ERROR -> {
                view.visibility = View.VISIBLE
            }
        }
    }
}