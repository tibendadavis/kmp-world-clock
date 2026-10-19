package com.example.kmpworldclock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.model.defaultCountries
import com.example.kmpworldclock.ui.CountrySelector
import com.example.kmpworldclock.ui.TimeDisplay
import com.example.kmpworldclock.util.currentTimeAt

@Composable
@Preview
fun App(countries: List<Country> = defaultCountries) {

    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("Not set yet buddy!") }

        Column(modifier = Modifier.padding(20.dp).safeContentPadding().fillMaxSize()) {
            TimeDisplay(
                text = timeAtLocation,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            CountrySelector(
                countries = countries,
                expanded = showCountries,
                onExpandedChange = { showCountries = it },
                onCountrySelected = { country ->
                    timeAtLocation = currentTimeAt(country.name, country.zone)
                },
            )
        }
    }
}
