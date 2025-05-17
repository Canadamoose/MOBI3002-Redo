package com.example.weatherapp.models

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Weather(
    val current: Current,
    val forecast: List<Forecast>
)

data class Current(
    val weatherIcon: Int,
    val condition: String,
    val temperature: Int,
    val precipitationType: String,
    val precipitationAmount: Double,
    val windDirection: String,
    val windSpeed: Double
)

data class Forecast(
    val date: String,
    val weatherIcon: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val condition: String,
    val precipitationType: String,
    val precipitationAmount: Double,
    val precipitationProbability: Int,
    val windDirection: String,
    val windSpeed: Double,
    val humidity: Int
)