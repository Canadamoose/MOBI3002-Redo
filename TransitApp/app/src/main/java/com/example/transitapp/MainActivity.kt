package com.example.transitapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.transitapp.ui.screens.*
import com.example.transitapp.ui.theme.TransitAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransitAppTheme {
                // initialize view model
                mainViewModel = viewModel()

                DisplayUI(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    var selectedIndex by remember { mutableIntStateOf(0) }

    // ask for permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        mainViewModel.onLocationPermissionResult(granted)
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // collect value of permission given
    val hasLocationPermission by mainViewModel.hasLocationPermission.collectAsState()

    // main UI
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Halifax")
                },
                // Colours from class example
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                NavigationBarItem(
                    label = { Text("Map") },
                    onClick = {
                        selectedIndex = 0
                        navController.navigate("map") },
                    selected = selectedIndex == 0,
                    icon = { Icon(
                        painter = painterResource(id = R.drawable.baseline_map_24),
                        contentDescription = "Map Icon") }
                )
                NavigationBarItem(
                    label = { Text("Alerts") },
                    onClick = {
                        selectedIndex = 1
                        navController.navigate("alerts") },
                    selected = selectedIndex == 1,
                    icon = { Icon(
                        painter = painterResource(id = R.drawable.outline_android_24),
                        contentDescription = "Alerts icon")}
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "map",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "map") {
                MapScreen(mainViewModel, hasLocationPermission)
            }
            composable(route = "alerts") {
                Alerts(mainViewModel)
            }
        }
    }
}
