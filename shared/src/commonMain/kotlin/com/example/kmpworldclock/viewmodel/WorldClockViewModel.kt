package com.example.kmpworldclock.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.model.defaultCountries
import com.example.kmpworldclock.storage.createSelectedCountriesStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Instant

class WorldClockViewModel : ViewModel() {

    private val storage = createSelectedCountriesStorage()

    var showCountries by mutableStateOf(false)
        private set

    var selectedCountries by mutableStateOf<List<Country>>(emptyList())
        private set

    var now by mutableStateOf<Instant>(Clock.System.now())
        private set

    init {
        viewModelScope.launch {
            val savedCodes = storage.load()
            selectedCountries = defaultCountries.filter { it.flagCode in savedCodes }
        }
        viewModelScope.launch {
            while (true) {
                now = Clock.System.now()
                delay(1000.milliseconds)
            }
        }
    }

    fun onExpandedChange(expanded: Boolean) {
        showCountries = expanded
    }

    fun onCountrySelected(country: Country) {
        if (country !in selectedCountries) {
            selectedCountries = selectedCountries + country
            persistSelection()
        }
    }

    fun onCountryRemoved(country: Country) {
        selectedCountries = selectedCountries - country
        persistSelection()
    }

    private fun persistSelection() {
        viewModelScope.launch {
            storage.save(selectedCountries.map { it.flagCode })
        }
    }
}
