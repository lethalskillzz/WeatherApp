package com.lethalskillzz.weatherapp.data.model

import com.squareup.moshi.Json
import org.threeten.bp.Instant

data class WeatherForecastResponse(
    @Json(name = "current_weather") val currentWeather: CurrentWeatherForecast
)

data class CurrentWeatherForecast(
    @Json(name = "time") val time: Instant,
    @Json(name = "temperature") val temperature: Double,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "weathercode") val weatherCode: Int
)
