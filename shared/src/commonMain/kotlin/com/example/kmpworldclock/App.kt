package com.example.kmpworldclock

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.model.defaultCountries
import com.example.kmpworldclock.util.currentTimeAt
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun App(countries: List<Country> = defaultCountries) {

    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("Not set yet buddy!") }

        Column(modifier = Modifier.padding(20.dp).safeContentPadding().fillMaxSize()) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries, onDismissRequest = { showCountries = false }) {
                    countries.forEach { (name, zone, image) ->
                        DropdownMenuItem(text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(image),
                                    modifier = Modifier.size(30.dp).padding(end = 10.dp),
                                    contentDescription = "$name flag"
                                )
                                Text(name)
                            }
                        }, onClick = {
                            timeAtLocation = currentTimeAt(name, zone)
                            showCountries = false
                        }

                        )
                    }

                }
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { showCountries = !showCountries }) {
                Text("Select Location")
            }
        }

    }
}
