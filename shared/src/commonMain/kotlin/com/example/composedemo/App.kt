package com.example.composedemo

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
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.Resource
import composedemo.shared.generated.resources.Res
import composedemo.shared.generated.resources.tz
import composedemo.shared.generated.resources.ae
import composedemo.shared.generated.resources.no
import composedemo.shared.generated.resources.jp
import composedemo.shared.generated.resources.gb
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock


data class Country(val name: String, val zone: TimeZone, val image: DrawableResource)

val defaultCountries = listOf<Country>(
    Country("Tanzania", TimeZone.of("Africa/Dar_es_Salaam"), Res.drawable.tz),
    Country("United Kingdom", TimeZone.of("Europe/London"), Res.drawable.gb),
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("Dubai", TimeZone.of("Asia/Dubai"), Res.drawable.ae),
    Country("Norway", TimeZone.of("Europe/Oslo"), Res.drawable.no)
)

fun currentTimeAt(location: String, zone: TimeZone): String {

    val timeFormat = LocalTime.Format({
        hour()
        char(':')
        minute()
        char(':')
        second()
    })

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time
    return "The time in $location is ${localTime.format(timeFormat)}"
}


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
                                    modifier = Modifier.size(20.dp).padding(end = 10.dp),
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





