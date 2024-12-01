package com.lethalskillzz.weatherapp.data.api.factory

import com.lethalskillzz.weatherapp.data.api.WeatherApi
import com.squareup.moshi.Moshi
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.open-meteo.com/v1/"

fun buildWeatherApi(): WeatherApi = Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
    .build()
    .create(WeatherApi::class.java)

private fun buildMoshi(): Moshi = Moshi.Builder()
    .add(MoshiInstantAdapter())
    .build()
