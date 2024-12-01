package com.lethalskillzz.weatherapp.util

sealed class State<out T> {
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val message: String, val cause: Throwable? = null) : State<Nothing>()
    data object Loading : State<Nothing>()
}
