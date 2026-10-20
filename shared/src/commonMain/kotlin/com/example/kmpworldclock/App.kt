package com.example.kmpworldclock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.model.defaultCountries
import com.example.kmpworldclock.ui.CountrySelector
import com.example.kmpworldclock.ui.TimeDisplay
import com.example.kmpworldclock.viewmodel.WorldClockViewModel

@Composable
@Preview
fun App(countries: List<Country> = defaultCountries) {

    MaterialTheme {
        val viewModel = viewModel { WorldClockViewModel() }

        Column(modifier = Modifier.padding(20.dp).safeContentPadding().fillMaxSize()) {
            TimeDisplay(
                text = viewModel.currentTime,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            CountrySelector(
                countries = countries,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                expanded = viewModel.showCountries,
                onExpandedChange = { viewModel.onExpandedChange(it) },
                onCountrySelected = { viewModel.onCountrySelected(it)},
            )
        }
    }
}
