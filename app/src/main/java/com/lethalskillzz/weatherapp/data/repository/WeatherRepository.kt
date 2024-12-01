package com.lethalskillzz.weatherapp.data.repository

import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse

interface WeatherRepository {
    suspend fun getWeatherForecast(lat: Double, lon: Double): WeatherForecastResponse
}
