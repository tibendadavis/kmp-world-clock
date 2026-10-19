package com.example.kmpworldclock

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    // Sets the initial size and position
    // of the window on screen
    val state = rememberWindowState(
        size = DpSize(400.dp, 350.dp),
        position = WindowPosition(820.dp, 400.dp)
    )
    // Sets the title of the application window
    // and uses the window state initialized above
    Window(
        title = "KMP World Clock",
        onCloseRequest = ::exitApplication,
        state = state,
        // Makes sure that the window is always on top
        // to make debugging and UI iteration easier
        alwaysOnTop = true
    ) {
        App()
    }
}