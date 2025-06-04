package com.example.weatherapp.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.MainViewModel

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
            AsyncImage(
                model = "https:${weather.current.condition.icon}",
                contentDescription = weather.current.condition.text,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(100.dp).width(100.dp)
            )

            //condition
            Text(text = weather.current.condition.text)

            //current temp
            Text(text = "${weather.current.temp_c}°C")

            //precipitation
            Text(text = "Precipitation: ${weather.current.precip_mm}mm")

            Text(text = "Wind: ${weather.current.wind_dir}," +
                    " ${weather.current.wind_kph}km/h")
        }
        else {
            Text(text = "Loading weather information")
        }
    }
}
