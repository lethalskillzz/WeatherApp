package com.lethalskillzz.weatherapp.domain.mapper

import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast

interface WeatherForecastMapper {
    fun map(response: WeatherForecastResponse): WeatherForecast
}
