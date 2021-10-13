package com.android.mobileweatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobileweatherapp.data.network.CityWeather
import com.android.mobileweatherapp.data.network.Forecast
import com.android.mobileweatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _cityWeather = MutableLiveData<CityWeather>()
    val cityWeather: LiveData<CityWeather> get() = _cityWeather

    private val _weatherForecast = MutableLiveData<Forecast>()
    val weatherForecast: LiveData<Forecast> get() = _weatherForecast

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            try {
                val cityWeather = async { weatherRepository.getCurrentWeather() }
                val weatherForecast = async { weatherRepository.getWeatherForecast() }
                val cityWeatherValue = cityWeather.await()
                val weatherForecastValue = weatherForecast.await()
                _cityWeather.value = cityWeatherValue
                Timber.d("City Weather: $cityWeather")
                _weatherForecast.value = weatherForecastValue
                Timber.d("Weather Forecast: $weatherForecast")
            } catch (e: Exception) {
                Timber.e("Failure: ${e.message}")
            }
        }
    }
}