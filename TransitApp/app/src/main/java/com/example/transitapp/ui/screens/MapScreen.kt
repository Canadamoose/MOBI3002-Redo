package com.example.transitapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.transitapp.MainViewModel
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.extension.compose.MapboxMap

@Composable
fun MapScreen(mainViewModel: MainViewModel) {

    val mapViewportState = mainViewModel.mapViewportState

    val gtfsFeed by mainViewModel.gtfs.collectAsState()
    val entities = gtfsFeed?.entityList

    mainViewModel.loadBusPositions()

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
