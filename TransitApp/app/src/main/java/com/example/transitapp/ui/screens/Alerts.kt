package com.example.transitapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.transitapp.MainViewModel

@Composable
fun Alerts(mainViewModel: MainViewModel) {

    val gtfsFeed by mainViewModel.gtfs.collectAsState()
    val entities = gtfsFeed?.entityList

    Column(modifier = Modifier.fillMaxSize()) {
        entities?.forEach { entity ->
            entity.alert?.let { alert ->
                AlertItem(
                    header = alert.headerText.translationList.firstOrNull()?.text ?: "Alert",
                    description = alert.descriptionText.translationList.firstOrNull()?.text ?: "No details"
                )
            }
        }
    }
}

@Composable
fun AlertItem(header: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }



}