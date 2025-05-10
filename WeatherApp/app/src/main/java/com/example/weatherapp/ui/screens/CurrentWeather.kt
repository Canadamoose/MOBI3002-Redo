package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R

@Composable
fun CurrentWeather() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()))  {

        // Weather image
        Image(
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = "Weather Icon")

        //Temp
        Text("72°F")

        //Condition
        Text("Sunny")

        //Precipitation
        Text("Precipitation: 0%")

        //Wind speed and direction
        Text("Wind: 5 mph East")
    }
}