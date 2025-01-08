package com.example.myapplication.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionBottomSheet(
    openSheet: MutableState<Boolean>,
) {
    if (openSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { openSheet.value = false },
            sheetState = rememberModalBottomSheetState(),
        ) {

                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,

                ) {
                    Text("Subscription")
                    Text("Please subscribe to access the content.")
                    Button(
                        onClick = {}
                    ) {
                        Text("Subscribe")
                    }
                }

        }
    }
}