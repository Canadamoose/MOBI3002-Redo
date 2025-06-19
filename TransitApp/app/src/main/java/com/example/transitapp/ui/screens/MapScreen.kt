package com.example.transitapp.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.transitapp.MainViewModel
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.extension.compose.style.layers.generated.CircleLayer
import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource


import com.mapbox.maps.plugin.locationcomponent.location



@Composable
fun MapScreen(mainViewModel: MainViewModel, hasLocationPermission: Boolean) {

    val gtfsFeed by mainViewModel.gtfs.collectAsState()
    val entities = gtfsFeed?.entityList

    val mapViewportState = mainViewModel.mapViewportState

    MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = mapViewportState
    )
    {
        if (hasLocationPermission){
            MapEffect(Unit) { mapView ->
                mapView.location.updateSettings {
                    //locationPuck = createDefault2DPuck(withBearing = true)
                    enabled = true
                    //puckBearing = PuckBearing.COURSE
                    puckBearingEnabled = true
                }

                // Zoom in to the user's location
                mapViewportState.transitionToFollowPuckState()
            }
        } else {
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

        entities?.forEach { entity ->
            entity.vehicle?.position?.let { pos ->
                CircleAnnotation(
                    point = Point.fromLngLat(
                        pos.longitude.toDouble(),
                        pos.latitude.toDouble()
                    )
                ) {
                    circleRadius = 8.0
                    circleColor = Color(0xffee4e8b)  // Pink color
                    circleStrokeWidth = 2.0
                    circleStrokeColor = Color(0xffffffff)  // White border
                }
            }
        }

    }
}
