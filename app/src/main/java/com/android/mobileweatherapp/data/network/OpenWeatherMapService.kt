package com.android.mobileweatherapp.data.network

import com.android.mobileweatherapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface OpenWeatherMapService {

    @GET("data/2.5/onecall?appid=${BuildConfig.apiKey}&exclude=minutely,hourly,alerts&units=metric")
    suspend fun getWeatherData(
        @Query(value = "lat") latitude: Double,
        @Query(value = "lon") longitude: Double
    ): CityWeather
}

object OpenWeatherMap {
    val openWeatherMapService: OpenWeatherMapService by lazy {
        retrofit.create(OpenWeatherMapService::class.java)
    }
}