package com.lethalskillzz.weatherapp.domain.mapper

import com.lethalskillzz.weatherapp.R
import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast
import com.lethalskillzz.weatherapp.util.resourceprovider.ResourceProvider
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

private const val TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

class WeatherForecastMapperImpl @Inject constructor(
    private val resourceProvider: ResourceProvider
) : WeatherForecastMapper {

    override fun map(response: WeatherForecastResponse): WeatherForecast {
        val condition = mapWeatherCode(response.currentWeather.weatherCode)
        val formattedTime = formatTime(response.currentWeather.time)
        return WeatherForecast(
            temperature = response.currentWeather.temperature,
            condition = condition,
            windSpeed = response.currentWeather.windSpeed,
            time = formattedTime
        )
    }

    private fun mapWeatherCode(weatherCode: Int): String =
        when (weatherCode) {
            0 -> resourceProvider.getString(R.string.condition_clear_ky)
            1, 2, 3 -> resourceProvider.getString(R.string.condition_partly_cloudy)
            else -> resourceProvider.getString(R.string.condition_unknown)
        }

    internal fun formatTime(time: org.threeten.bp.Instant): String {
        val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT)
            .withZone(ZoneId.systemDefault())
        return formatter.format(time)
    }
}
