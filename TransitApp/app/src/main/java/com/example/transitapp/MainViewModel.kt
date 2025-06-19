package com.example.transitapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.transit.realtime.GtfsRealtime
import com.google.transit.realtime.GtfsRealtime.FeedMessage
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainViewModel : ViewModel() {
    private val _gtfs = MutableStateFlow<GtfsRealtime.FeedMessage?>(null)
    val gtfs = _gtfs.asStateFlow()

    private val _gtfsAlerts = MutableStateFlow<FeedMessage?>(null)
    val gtfsAlerts = _gtfsAlerts.asStateFlow()

    val mapViewportState = MapViewportState()

    private val _hasLocationPermission = MutableStateFlow(false)
    val hasLocationPermission: StateFlow<Boolean> = _hasLocationPermission

    init {
        loadBusPositions()
        viewModelScope.launch {
            while (true) {
                delay(15000) // Refresh every 15 seconds
                loadBusPositions()
            }
        }
    }

    fun onLocationPermissionResult(granted: Boolean) {
        _hasLocationPermission.value = granted
    }


    fun loadAlerts() {
        viewModelScope.launch {
            try {
                val url = URL("https://gtfs.halifax.ca/realtime/Alert/Alerts.pb")
                val feed = withContext(Dispatchers.IO) {
                    FeedMessage.parseFrom(url.openStream())
                }
                _gtfsAlerts.value = feed
            } catch (e: Exception) {
                Log.e("AlertsError", "Failed to load alerts", e)
            }
        }
    }

    fun loadBusPositions() {
        viewModelScope.launch {
            try {
                val url = URL("https://gtfs.halifax.ca/realtime/Vehicle/VehiclePositions.pb")
                // Run code (which is blocking) on a background thread optimized for I/O,
                // and suspend the coroutine until it's done.
                val feed = withContext(Dispatchers.IO) {
                    GtfsRealtime.FeedMessage.parseFrom(url.openStream())
                }
                Log.d("TESTING", feed.toString())
                _gtfs.value = feed
            } catch (e: Exception) {
                Log.e("TESTING", e.toString() )
            }
        }
    }
}