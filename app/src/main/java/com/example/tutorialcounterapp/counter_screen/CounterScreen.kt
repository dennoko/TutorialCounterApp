package com.example.tutorialcounterapp.counter_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorialcounterapp.R

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = 0.2f))
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_settings_24),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Text(
                text = "Title",
                fontSize = 28.sp
            )

            Box(
                modifier = Modifier.size(48.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.outline_cancel_24),
                contentDescription = null
            )

            Box(
                modifier = Modifier.weight(2f)
            ){
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.padding(20.dp)
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.outline_arrow_drop_down_24),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Box(
                modifier = Modifier.weight(1f)
            ){
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.padding(20.dp)
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.outline_arrow_drop_up_24),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.outline_add_circle_outline_24),
            contentDescription = null,
            modifier = Modifier.size(72.dp))
    }
}