package com.lethalskillzz.weatherapp.data.api

import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") currentWeather: Boolean = true
    ): WeatherForecastResponse
}
