package com.lethalskillzz.weatherapp.data.repository.impl

import com.lethalskillzz.weatherapp.data.api.WeatherApi
import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse
import com.lethalskillzz.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherForecast(lat: Double, lon: Double): WeatherForecastResponse =
        api.getWeatherForecast(lat, lon)
}
