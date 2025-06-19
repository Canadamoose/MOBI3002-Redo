package com.example.transitapp.ui.screens

import androidx.compose.runtime.Composable
import com.example.transitapp.MainViewModel
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState

@Composable
fun Other(mainViewModel: MainViewModel) {

    val mapViewportState = mainViewModel.mapViewportState

}