package com.lethalskillzz.weatherapp.util.resourceprovider.impl

import android.content.Context
import androidx.annotation.StringRes
import com.lethalskillzz.weatherapp.util.resourceprovider.ResourceProvider
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun getString(@StringRes id: Int, vararg args: Any?): String =
        context.getString(id, *args)
}