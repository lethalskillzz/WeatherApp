package com.lethalskillzz.weatherapp.domain.usecase

import com.lethalskillzz.weatherapp.R
import com.lethalskillzz.weatherapp.data.model.CurrentWeatherForecast
import com.lethalskillzz.weatherapp.data.model.WeatherForecastResponse
import com.lethalskillzz.weatherapp.data.repository.WeatherRepository
import com.lethalskillzz.weatherapp.domain.mapper.WeatherForecastMapper
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast
import com.lethalskillzz.weatherapp.util.State
import com.lethalskillzz.weatherapp.util.resourceprovider.ResourceProvider
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.threeten.bp.Instant

class GetWeatherForecastUseCaseTest {

    private val mockRepository: WeatherRepository = mockk()
    private val mockMapper: WeatherForecastMapper = mockk()
    private val mockResourceProvider: ResourceProvider = mockk()

    private val useCase = GetWeatherForecastUseCase(
        repository = mockRepository,
        mapper = mockMapper,
        provider = mockResourceProvider
    )

    @Test
    fun `GIVEN valid coordinates WHEN repository returns data THEN return State Success with mapped data`() =
        runTest {
            val lat = 53.619653
            val lon = 10.079969

            val response = WeatherForecastResponse(
                currentWeather = CurrentWeatherForecast(
                    time = Instant.parse("2024-12-01T10:15:00Z"),
                    temperature = 22.5,
                    windSpeed = 5.3,
                    weatherCode = 1
                )
            )

            val mappedWeather = WeatherForecast(
                temperature = 22.5,
                condition = "Partly Cloudy",
                windSpeed = 5.3,
                time = "2024-12-01 10:15:00"
            )

            coEvery { mockRepository.getWeatherForecast(lat, lon) } returns response
            every { mockMapper.map(response) } returns mappedWeather

            val result = useCase.invoke(lat, lon)

            assertTrue(result is State.Success)
            assertEquals(mappedWeather, (result as State.Success).data)

            coVerify(exactly = 1) { mockRepository.getWeatherForecast(lat, lon) }
            verify(exactly = 1) { mockMapper.map(response) }
            verify { mockResourceProvider wasNot Called }
        }

    @Test
    fun `GIVEN repository throws exception WHEN use case is invoked THEN return State Error with message`() =
        runTest {
            val lat = 53.619653
            val lon = 10.079969
            val exception = RuntimeException("Network error")
            val errorMessage = "An error occurred"

            coEvery { mockRepository.getWeatherForecast(lat, lon) } throws exception
            every { mockResourceProvider.getString(R.string.error_message) } returns errorMessage

            val result = useCase.invoke(lat, lon)

            assertTrue(result is State.Error)
            assertEquals(errorMessage, (result as State.Error).message)
            assertEquals(exception, result.cause)

            coVerify(exactly = 1) { mockRepository.getWeatherForecast(lat, lon) }
            verify(exactly = 1) { mockResourceProvider.getString(R.string.error_message) }
            verify { mockMapper wasNot Called }
        }
}
