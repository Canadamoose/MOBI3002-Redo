package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class Current(
    val condition: Condition,
    val temp_c: Double,
    val precip_mm: Double,
    val wind_dir: String,
    val wind_kph: Double
)

data class ForecastObject(
    val date: String,
    val day: Day
)

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: Condition,
    val totalprecip_mm: Double,
    val maxwind_kph: Double,
    val daily_chance_of_rain: Int,
    val avghumidity: Int
)

data class Forecast(
    val forecastday: List<ForecastObject>
)

data class Condition(
    val text: String,
    @SerializedName("icon") val icon: String
    )