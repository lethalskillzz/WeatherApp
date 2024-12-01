package com.lethalskillzz.weatherapp.data.repository.di

import com.lethalskillzz.weatherapp.data.api.WeatherApi
import com.lethalskillzz.weatherapp.data.repository.WeatherRepository
import com.lethalskillzz.weatherapp.data.repository.impl.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(weatherApi)
}
