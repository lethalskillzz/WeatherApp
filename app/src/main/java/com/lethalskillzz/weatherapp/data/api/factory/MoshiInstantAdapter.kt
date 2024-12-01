package com.lethalskillzz.weatherapp.data.api.factory

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant

class MoshiInstantAdapter {

    @FromJson
    fun fromJson(value: String): Instant {
        return Instant.parse(value)
    }

    @ToJson
    fun toJson(value: Instant): String {
        return value.toString()
    }
}
