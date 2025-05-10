package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun DailyForecast() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 25.dp)
            .verticalScroll(state = rememberScrollState())) {

        //Date
        Text("Tomorrow")
        // Weather image
        Image(
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = "Weather Icon"
        )
        //Temp
        Text("72°F")
        //Condition
        Text("Sunny")
        //Precipitation
        Text("Precipitation: 0%")
        //Wind speed and direction
        Text("Wind: 5 mph East")

        //2nd day
        Spacer(Modifier.height(50.dp))
        //Date
        Text("The next Day")
        // Weather image
        Image(
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = "Weather Icon"
        )
        //Temp
        Text("72°F")
        //Condition
        Text("Sunny")
        //Precipitation
        Text("Precipitation: 0%")
        //Wind speed and direction
        Text("Wind: 5 mph East")

        //3rd day
        Spacer(Modifier.height(50.dp))
        //Date
        Text("The other next day")
        // Weather image
        Image(
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = "Weather Icon"
        )
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