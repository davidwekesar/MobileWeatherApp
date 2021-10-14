package com.android.mobileweatherapp.viewmodels

import androidx.lifecycle.*
import com.android.mobileweatherapp.data.network.CityWeather
import com.android.mobileweatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _cityWeather = MutableLiveData<CityWeather>()
    val cityWeather: LiveData<CityWeather> get() = _cityWeather

    fun getWeatherData(coordinates: Coordinates) {
        viewModelScope.launch {
            try {
                val latitude = coordinates.latitude
                val longitude = coordinates.longitude
                _cityWeather.value = weatherRepository.getWeatherData(latitude, longitude)
                Timber.i("Latitude: $latitude, Longitude: $longitude")
            } catch (e: Exception) {
                Timber.e("Failure: ${e.message}")
            }
        }
    }
}

data class Coordinates(val latitude: Double, val longitude: Double)