package com.android.mobileweatherapp.di

import com.android.mobileweatherapp.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository()
    }
}