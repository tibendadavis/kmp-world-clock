package com.example.kmpworldclock.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpworldclock.model.Country
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds

class WorldClockViewModel : ViewModel() {

    var showCountries by mutableStateOf(false)
        private set

    var selectedCountries by mutableStateOf<List<Country>>(emptyList())
        private set

   var now by mutableStateOf(Clock.System.now())
        private set

    init {
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
        }
    }
}
