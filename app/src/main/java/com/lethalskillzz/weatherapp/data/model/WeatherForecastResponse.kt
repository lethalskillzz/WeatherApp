package com.lethalskillzz.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
data class WeatherForecastResponse(
    @Json(name = "current_weather") val currentWeather: CurrentWeatherForecast
)

@JsonClass(generateAdapter = true)
data class CurrentWeatherForecast(
    @Json(name = "time") val time: Instant,
    @Json(name = "temperature") val temperature: Double,
    @Json(name = "windspeed") val windSpeed: Double,
    @Json(name = "weathercode") val weatherCode: Int
)
