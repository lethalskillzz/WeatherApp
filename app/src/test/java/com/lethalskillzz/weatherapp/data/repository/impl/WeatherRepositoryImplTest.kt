package com.lethalskillzz.weatherapp.data.repository.impl

import com.lethalskillzz.weatherapp.data.api.WeatherApi
import com.lethalskillzz.weatherapp.data.model.CurrentWeatherForecast
import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.Instant

class WeatherRepositoryImplTest {

    private val mockApi: WeatherApi = mockk()
    private val repository = WeatherRepositoryImpl(mockApi)

    @Test
    fun `GIVEN valid lat and lon WHEN getWeatherForecast is called THEN return WeatherForecastResponse`() =
        runTest {
            val lat = 53.619653
            val lon = 10.079969
            val expectedResponse = WeatherForecastResponse(
                currentWeather = CurrentWeatherForecast(
                    time = Instant.parse("2024-12-01T10:15:00Z"),
                    temperature = 22.5,
                    windSpeed = 5.3,
                    weatherCode = 1
                )
            )
            coEvery { mockApi.getWeatherForecast(lat, lon) } returns expectedResponse

            val result = repository.getWeatherForecast(lat, lon)

            assertEquals(expectedResponse, result)
            coVerify(exactly = 1) { mockApi.getWeatherForecast(lat, lon) }
        }

    @Test
    fun `GIVEN API throws exception WHEN getWeatherForecast is called THEN exception is propagated`() =
        runTest {
            val lat = 53.619653
            val lon = 10.079969
            val exception = RuntimeException("API error")
            coEvery { mockApi.getWeatherForecast(lat, lon) } throws exception

            try {
                repository.getWeatherForecast(lat, lon)
            } catch (e: Exception) {
                assertEquals(exception, e)
            }

            coVerify(exactly = 1) { mockApi.getWeatherForecast(lat, lon) }
        }
}