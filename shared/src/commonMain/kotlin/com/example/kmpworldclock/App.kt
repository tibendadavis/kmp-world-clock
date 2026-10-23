package com.example.kmpworldclock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.model.defaultCountries
import com.example.kmpworldclock.ui.ClockCard
import com.example.kmpworldclock.ui.CountrySelector
import com.example.kmpworldclock.viewmodel.WorldClockViewModel

@Composable
@Preview
fun App(countries: List<Country> = defaultCountries) {
    MaterialTheme {
        val viewModel = viewModel { WorldClockViewModel() }
        Column(modifier = Modifier.safeContentPadding().fillMaxSize()) {
            if (viewModel.selectedCountries.isEmpty()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(2f).fillMaxWidth()
                ) {
                    Text("No clocks yet, add one below")
                }
            } else {
                BoxWithConstraints(modifier = Modifier.weight(2f).fillMaxWidth()) {
                    if (maxWidth >= 600.dp) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 280.dp),
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            gridItems(viewModel.selectedCountries, key = { it.flagCode }) {
                                ClockCard(
                                    it,
                                    now = viewModel.now,
                                    onRemove = { viewModel.onCountryRemoved(it) },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(viewModel.selectedCountries, key = { it.flagCode }) {
                                ClockCard(
                                    it,
                                    now = viewModel.now,
                                    onRemove = { viewModel.onCountryRemoved(it) },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            }
                        }
                    }
                }
            }
            CountrySelector(
                countries = countries - viewModel.selectedCountries.toSet(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                expanded = viewModel.showCountries,
                onExpandedChange = { viewModel.onExpandedChange(it) },
                onCountrySelected = { viewModel.onCountrySelected(it) },
            )
        }
    }
}
