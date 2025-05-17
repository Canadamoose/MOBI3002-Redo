package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R

@Composable
fun DailyForecast(mainViewModel: MainViewModel) {

    val weather = mainViewModel.weather.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())) {


        if (weather != null) {
            weather.forecast.forEach { forecast ->


                Text(text = forecast.date)

                Image(
                    painter = painterResource(id = ("${forecast.weatherIcon}").toInt()),
                    contentDescription = "Weather Icon"
                )

                Text(text = "High: ${forecast.maxTemp}°C, Low: ${forecast.minTemp}°C")

                Text(text = forecast.condition)

                if (forecast.precipitationAmount != 0.0){
                    Text(text = "Precipitation: ${forecast.precipitationType}," +
                            " ${forecast.precipitationAmount}mm," +
                            " ${forecast.precipitationProbability}%")
                }

                Text(text = "Wind: ${forecast.windDirection}," +
                        " ${forecast.windSpeed}km/h")

                Text(text = "Humidity: ${forecast.humidity}%")

                //Give space
                Spacer(Modifier.height(50.dp))
            }
        }
    }
}