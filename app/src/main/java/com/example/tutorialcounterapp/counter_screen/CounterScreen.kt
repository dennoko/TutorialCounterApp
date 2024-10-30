package com.example.tutorialcounterapp.counter_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.tutorialcounterapp.R
import com.example.tutorialcounterapp.ui.theme.TutorialCounterAppTheme

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    myAppViewModel: CounterScreenViewModel = viewModel(factory = CounterScreenViewModel.Factory),
    onSettingClicked: () -> Unit = {},
    onDeleteClicked: (String) -> Unit = {},
    onDecrementClicked: (String) -> Unit = {},
    onIncrementClicked: (String) -> Unit = {},
    onAddClicked: (String) -> Unit = {},
) {
    val savedItems by myAppViewModel.uiState.collectAsState()
    var itemSet by remember {
        mutableStateOf(savedItems.itemNameSet)
    }
    var itemName by remember { mutableStateOf("") }
    var newItemName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val itemNames = savedItems.itemNameSet.toList()
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

        itemNames.forEach { itemName ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onDeleteClicked(itemName) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_cancel_24),
                        contentDescription = null
                    )
                }

                Text(
                    text = itemName,
                    fontSize = 28.sp,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { onDecrementClicked(itemName) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_arrow_drop_down_24),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Text(
                    text = "",
                    fontSize = 28.sp,
                    modifier = Modifier.size(48.dp)
                )

                IconButton(onClick = { onIncrementClicked(itemName) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_arrow_drop_up_24),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = newItemName,
                onValueChange = {
                    newItemName = it
                },
                modifier = Modifier.weight(4f)
            )
            
            Spacer(modifier = Modifier.size(10.dp))

            Button(
                onClick = {
                    onAddClicked(newItemName)
                    newItemName = ""
                          },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
                ) {
                Text(
                    text = "Add"
                )
            }
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