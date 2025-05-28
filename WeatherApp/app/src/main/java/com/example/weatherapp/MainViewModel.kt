package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Weather
import com.example.weatherapp.services.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    //Retrofit instance
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Initialize interface
    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

    init {
        viewModelScope.launch {
            try {
                _weather.value = weatherService.getWeather(
                    key = "3f3eec6216d543e8b72211054252105",
                    q = "Halifax",
                    days = 3,
                    aqi = "no",
                    alerts = "no"
                )
            } catch (e: Exception) {
                Log.e("Weather", "API Error", e)
            }
        }

//        val weather = Weather(
//            current = Current(
//                weatherIcon = R.drawable.weather_icon,
//                condition = "Overcast",
//                temp_c = 15,
//                precipitationType = "Rain",
//                precipitationAmount = 2.2,
//                windDirection = "SW",
//                windSpeed = 12.8
//            ),
//            forecast = listOf(
//                Forecast(
//                    date = "2025-05-15",
//                    weatherIcon = R.drawable.weather_icon,
//                    maxTemp = 23,
//                    minTemp = 10,
//                    condition = "Sunny",
//                    precipitationType = "None",
//                    precipitationAmount = 0.0,
//                    precipitationProbability = 0,
//                    windDirection = "NE",
//                    windSpeed = 4.5,
//                    humidity = 50,
//                ),
//                Forecast(
//                    date = "2025-05-16",
//                    weatherIcon = R.drawable.weather_icon,
//                    maxTemp = 7,
//                    minTemp = 2,
//                    condition = "Rain",
//                    precipitationType = "RAIN",
//                    precipitationAmount = 6.0,
//                    precipitationProbability = 100,
//                    windDirection = "S",
//                    windSpeed = 10.2,
//                    humidity = 100,
//                ),
//                Forecast(
//                    date = "2025-05-17",
//                    weatherIcon = R.drawable.weather_icon,
//                    maxTemp = 0,
//                    minTemp = -3,
//                    condition = "Overcast",
//                    precipitationType = "Snow",
//                    precipitationAmount = 2.4,
//                    precipitationProbability = 29,
//                    windDirection = "N",
//                    windSpeed = 20.0,
//                    humidity = 36,
//                )
//            )
//        )

    }
}