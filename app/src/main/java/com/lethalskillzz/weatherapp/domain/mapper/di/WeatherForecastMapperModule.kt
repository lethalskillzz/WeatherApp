package com.lethalskillzz.weatherapp.domain.mapper.di

import com.lethalskillzz.weatherapp.domain.mapper.WeatherForecastMapper
import com.lethalskillzz.weatherapp.domain.mapper.WeatherForecastMapperImpl
import com.lethalskillzz.weatherapp.util.resourceprovider.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherForecastMapperModule {

    @Provides
    @Singleton
    fun provideWeatherForecastMapper(
        resourceProvider: ResourceProvider
    ): WeatherForecastMapperImpl = WeatherForecastMapperImpl(resourceProvider)
}

@Module
@InstallIn(SingletonComponent::class)
interface WeatherForecastMapperBindingModule {

    @Binds
    @Singleton
    fun bindWeatherForecastMapper(weatherForecastMapperImpl: WeatherForecastMapperImpl): WeatherForecastMapper
}
