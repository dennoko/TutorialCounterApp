package com.example.tutorialcounterapp.counter_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tutorialcounterapp.R
import com.example.tutorialcounterapp.ui.theme.TutorialCounterAppTheme

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    uiState: CounterScreenUiState = CounterScreenUiState(),
    onSettingClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {},
    onSaveClicked: (String, String) -> Unit = { _, _ -> },
    onDecrementClicked: () -> Unit = {},
    onIncrementClicked: () -> Unit = {},
    onAddClicked: () -> Unit = {},
) {
    val savedItems = uiState.itemNameSet

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
            IconButton(onClick = { onSettingClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_settings_24),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }

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
            verticalAlignment = Alignment.CenterVertically,
        ){
            var itemName by remember { mutableStateOf(savedItems.firstOrNull() ?:"") }
            val previousItemName by remember { mutableStateOf(itemName) }
            IconButton(onClick = { onDeleteClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_cancel_24),
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier.weight(2f)
            ){
                LaunchedEffect(savedItems) {
                    itemName = savedItems.firstOrNull() ?: ""
                }
                OutlinedTextField(
                    value = itemName,
                    onValueChange = {
                        itemName = it
                    },
                    modifier = Modifier.padding(20.dp)
                )
            }

            // TODO: SAVE ボタンの追加。previousItemName と itemName の値が異なる場合に changeTextFieldValue を呼び出す
            IconButton(onClick = { onSaveClicked(previousItemName, itemName); Log.d("CounterScreenTest", "pre: ${previousItemName} new: ${itemName}") }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp))
            }

            IconButton(onClick = { onDecrementClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_arrow_drop_down_24),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f)
            ){
                val text by remember { mutableStateOf("") }
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(56.dp)
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                )
            }

            IconButton(onClick = { onIncrementClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_arrow_drop_up_24),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        IconButton(onClick = { onAddClicked() }) {
            Icon(
                painter = painterResource(id = R.drawable.outline_add_circle_outline_24),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    TutorialCounterAppTheme {
        CounterScreen()
    }
}