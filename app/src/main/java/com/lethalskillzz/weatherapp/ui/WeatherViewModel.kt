package com.lethalskillzz.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast
import com.lethalskillzz.weatherapp.domain.usecase.GetWeatherForecastUseCase
import com.lethalskillzz.weatherapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DELAY_INTERVAL = 10_000L

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: GetWeatherForecastUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<State<WeatherForecast>>(State.Loading)
    val weatherState: StateFlow<State<WeatherForecast>> = _weatherState

    private val locations = listOf(
        53.619653 to 10.079969,
        53.080917 to 8.847533,
        52.378385 to 9.794862,
        52.496385 to 13.444041,
        53.866865 to 10.739542,
        54.304540 to 10.152741,
        54.797277 to 9.491039,
        52.426412 to 10.821392,
        53.542788 to 8.613462,
        53.141598 to 8.242565
    )

    init {
        updateWeather()
    }

    // To interrupt the loop during testing
    internal var stopUpdates: Boolean = false

    private fun updateWeather() {
        viewModelScope.launch {
            var index = 0
            while (!stopUpdates) {
                val (lat, lon) = locations[index]
                _weatherState.update { useCase(lat, lon) }
                index = (index + 1) % locations.size
                delay(DELAY_INTERVAL)
            }
        }
    }
}
