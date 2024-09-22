package com.example.tutorialcounterapp.counter_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .then(modifier)
    ) {
        Text(text = "Counter Screen")
    }
}