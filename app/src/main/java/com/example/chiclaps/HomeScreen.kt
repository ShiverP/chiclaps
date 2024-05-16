package com.example.chiclaps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
// This is where home screen goes basically
    Column {
        TopAppBar(
            title = { Text("Chiclaps Home") },
            Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
        )

        Spacer(modifier = Modifier.height(16.dp))
        DeviceDashboard()
    }
}

@Composable
fun DeviceDashboard(){
    // Grid/list/overview/etc of devices to be added later or whatever
    Text("Smart home devices to be shown here at a later date")
}

@Composable
@Preview
fun homeScreenPreview(){
    HomeScreen()
}