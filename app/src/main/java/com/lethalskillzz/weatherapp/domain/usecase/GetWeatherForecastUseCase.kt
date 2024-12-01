package com.lethalskillzz.weatherapp.domain.usecase

import com.lethalskillzz.weatherapp.R
import com.lethalskillzz.weatherapp.data.repository.WeatherRepository
import com.lethalskillzz.weatherapp.domain.mapper.WeatherForecastMapper
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast
import com.lethalskillzz.weatherapp.util.State
import com.lethalskillzz.weatherapp.util.resourceprovider.ResourceProvider
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val mapper: WeatherForecastMapper,
    private val provider: ResourceProvider
) {

    suspend operator fun invoke(lat: Double, lon: Double): State<WeatherForecast> {
        return try {
            val response = repository.getWeatherForecast(lat, lon)
            val weather = mapper.map(response)
            State.Success(weather)
        } catch (e: Exception) {
            State.Error(provider.getString(R.string.error_message), e)
        }
    }
}
