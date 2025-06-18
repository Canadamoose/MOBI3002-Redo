package com.example.transitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.transitapp.ui.theme.TransitAppTheme
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransitAppTheme {
                DisplayMap()
            }
        }
    }
}

@Composable
fun DisplayMap() {
    // This should be stored in View Model.
    val mapViewportState = MapViewportState()

    MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = mapViewportState
    )
    {
        // if statement for is permission granted or not (view state variables)
        LaunchedEffect(Unit) {
            mapViewportState.flyTo(
                cameraOptions = CameraOptions.Builder()
                    .center(Point.fromLngLat(-63.6027, 44.6544)) // Halifax city center
                    .zoom(14.0)
                    .pitch(0.0)
                    .bearing(0.0)
                    .build()
            )
        }
    }
}