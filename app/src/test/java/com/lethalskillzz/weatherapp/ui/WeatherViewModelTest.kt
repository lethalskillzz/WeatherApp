package com.lethalskillzz.weatherapp.ui

import app.cash.turbine.test
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast
import com.lethalskillzz.weatherapp.domain.usecase.GetWeatherForecastUseCase
import com.lethalskillzz.weatherapp.util.State
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockUseCase = mockk<GetWeatherForecastUseCase>()

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN use case returns success WHEN updateWeather is called THEN weatherState emits success`() =
        runTest {
            val forecast = WeatherForecast(
                temperature = 20.0,
                condition = "Clear Sky",
                windSpeed = 5.0,
                time = "2024-12-01 10:15:00"
            )
            val successState = State.Success(forecast)
            coEvery { mockUseCase(any(), any()) } returns successState

            viewModel = WeatherViewModel(mockUseCase)

            viewModel.weatherState.test {
                assertEquals(State.Loading, awaitItem())

                advanceTimeBy(10_000L)
                assertEquals(successState, awaitItem())

                viewModel.stopUpdates = true
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `GIVEN use case returns error WHEN updateWeather is called THEN weatherState emits error`() =
        runTest {
            val errorState = State.Error("An error occurred")
            coEvery { mockUseCase(any(), any()) } returns errorState

            viewModel = WeatherViewModel(mockUseCase)

            viewModel.weatherState.test {
                assertEquals(State.Loading, awaitItem())

                advanceTimeBy(10_000L)
                assertEquals(errorState, awaitItem())

                viewModel.stopUpdates = true
                cancelAndConsumeRemainingEvents()
            }
        }
}
