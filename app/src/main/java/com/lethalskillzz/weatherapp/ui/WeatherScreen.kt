package com.lethalskillzz.weatherapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lethalskillzz.weatherapp.R
import com.lethalskillzz.weatherapp.domain.model.WeatherForecast
import com.lethalskillzz.weatherapp.util.State

private const val PADDING = 16
private const val FONT_SIZE = 18
private const val VERTICAL_SPACING = 8

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(PADDING.dp),
        contentAlignment = Alignment.Center
    ) {
        when (val weatherState = viewModel.weatherState.collectAsState().value) {
            is State.Loading -> {
                Text(
                    text = stringResource(R.string.loading_text),
                    fontSize = FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )
            }

            is State.Success -> {
                val weather = weatherState.data
                WeatherInfo(weather = weather)
            }

            is State.Error -> {
                Text(
                    text = weatherState.message,
                    fontSize = FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun WeatherInfo(weather: WeatherForecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACING.dp)
    ) {
        Text(
            text = stringResource(R.string.time_label, weather.time),
            fontSize = FONT_SIZE.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.temperature_label, weather.temperature),
            fontSize = FONT_SIZE.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.wind_speed_label, weather.windSpeed),
            fontSize = FONT_SIZE.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.condition_label, weather.condition),
            fontSize = FONT_SIZE.sp,
            textAlign = TextAlign.Center
        )
    }
}
