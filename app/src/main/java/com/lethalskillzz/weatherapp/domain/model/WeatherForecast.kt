package com.lethalskillzz.weatherapp.domain.model

data class WeatherForecast(
    val temperature: Double,
    val condition: String,
    val windSpeed: Double,
    val time: String
)
