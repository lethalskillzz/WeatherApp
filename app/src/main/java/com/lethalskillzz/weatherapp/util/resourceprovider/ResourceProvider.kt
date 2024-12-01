package com.lethalskillzz.weatherapp.util.resourceprovider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg args: Any?): String
}
