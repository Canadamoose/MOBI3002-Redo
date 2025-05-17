package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R

@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {

    val weather = mainViewModel.weather.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()))  {

        // Weather image
        if (weather != null) {
            Image(
                painter = painterResource(id = ("${weather.current.weatherIcon}").toInt()),
                contentDescription = "Weather Icon")

            Text(text = weather.current.condition)

            Text(text = "${weather.current.temperature}°C")

            Text(text = "Precipitation: ${weather.current.precipitationType}," +
                    " ${weather.current.precipitationAmount}mm")

            Text(text = "Wind: ${weather.current.windDirection}," +
                    " ${weather.current.windSpeed}km/h")
        }
        else {
            Text(text = "Weather not found")
        }
    }
}