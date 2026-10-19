package com.example.kmpworldclock.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TimeDisplay(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = TextStyle(fontSize = 20.sp),
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
    )
}
