package com.lethalskillzz.weatherapp.data.api.factory

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant

class MoshiInstantAdapter {

    @FromJson
    fun fromJson(value: String): Instant {
        return try {
            Instant.parse(normalizeDatetime(value))
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid ISO 8601 format: $value", e)
        }
    }

    @ToJson
    fun toJson(value: Instant): String {
        return value.toString()
    }

    private fun normalizeDatetime(value: String): String {
        return when {
            value.matches(Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}Z?")) -> "$value:00.000Z"
            value.matches(Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z?")) -> "$value.000Z"
            value.matches(Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z")) -> value
            else -> throw IllegalArgumentException("Unsupported ISO 8601 datetime format: $value")
        }
    }
}