package com.example.tutorialcounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorialcounterapp.counter_screen.CounterScreen
import com.example.tutorialcounterapp.counter_screen.CounterScreenUiState
import com.example.tutorialcounterapp.counter_screen.CounterScreenViewModel
import com.example.tutorialcounterapp.setting_screen.SettingScreen
import com.example.tutorialcounterapp.ui.theme.TutorialCounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val counterScreenVM: CounterScreenViewModel = viewModel(factory = CounterScreenViewModel.Factory)
            val counterScreenUiState: CounterScreenUiState by counterScreenVM.uiState.collectAsState()
            TutorialCounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Counter", modifier = Modifier.padding(innerPadding)){
                        composable("Counter") {
                            CounterScreen(
                                uiState = counterScreenUiState,
                                onSettingClicked = {navController.navigate(route = "Setting")},
                                onDeleteClicked = {counterScreenVM.saveItems(emptySet())},
                                onSaveClicked = {previousItemName, newItemName -> },
                                onDecrementClicked = {},
                                onIncrementClicked = {},
                                onAddClicked = {},
                            )
                        }
                        composable("Setting") { SettingScreen()}
                    }
                }
            }
        }
    }
}
