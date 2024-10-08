package com.example.tutorialcounterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorialcounterapp.counter_screen.CounterScreen
import com.example.tutorialcounterapp.setting_screen.SettingScreen
import com.example.tutorialcounterapp.ui.theme.TutorialCounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TutorialCounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Counter", modifier = Modifier.padding(innerPadding)){
                        composable("Counter") { CounterScreen(onSettingClicked = {navController.navigate(route = "Setting")})}
                        composable("Setting") { SettingScreen()}
                    }
                }
            }
        }
    }
}
