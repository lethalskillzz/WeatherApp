package com.lethalskillzz.weatherapp.data.api.di

import com.lethalskillzz.weatherapp.data.api.WeatherApi
import com.lethalskillzz.weatherapp.data.api.factory.buildWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi = buildWeatherApi()
}
