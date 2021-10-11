package com.android.mobileweatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobileweatherapp.data.network.CityWeather
import com.android.mobileweatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository()

    private val _cityWeather = MutableLiveData<CityWeather>()
    val cityWeather: LiveData<CityWeather> get() = _cityWeather

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            try {
                val cityWeather = weatherRepository.getCurrentWeather()
                _cityWeather.value = cityWeather
                Log.d("WeatherViewModel", "City Weather value: $cityWeather")
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Failure: ${e.message}")
            }
        }
    }
}