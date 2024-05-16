package com.example.chiclaps

import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.selects.select

@Composable
fun SettingsScreen(){
    var isLightThemeSelected by remember { mutableSetOf(true) }

    Column {
        Text("Change Theme")
        Row {
            RadioButton(
                selected = isLightThemeSelected,
                onClick = { isLightThemeSelected = true }
            )
            
            Text("Light Theme")
            RadioButton(
                selected = !isLightThemeSelected,
                onClick = { isLightThemeSelected = false }
            )
            
            Text("Dark Theme")
        }
    }
}