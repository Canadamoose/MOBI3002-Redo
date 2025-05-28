package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.MainViewModel

@Composable
fun DailyForecast(mainViewModel: MainViewModel) {

    val weather = mainViewModel.weather.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())) {


        if (weather != null) {
            weather.forecast.forecastday.forEach { forecast ->

                //date
                Text(text = forecast.date)

                //Weather image
                AsyncImage(
                    model = "https:${forecast.day.condition.icon}",
                    contentDescription = forecast.day.condition.text
                )


                Text(text = "High: ${forecast.day.maxtemp_c}°C, Low: ${forecast.day.mintemp_c}°C")

                //condition
                Text(text = forecast.day.condition.text)


                //precipitation
                Text(text = "Precipitation chance: ${forecast.day.daily_chance_of_rain}%," +
                        " ${forecast.day.totalprecip_mm}mm"
                )


                Text(text = "Max wind speed: ${forecast.day.maxwind_kph}km/h")

                Text(text = "Humidity: ${forecast.day.avghumidity}%")

                //Give space
                Spacer(Modifier.height(50.dp))
            }
        } else {
            Text(text = "Weather not found")
        }
    }
}