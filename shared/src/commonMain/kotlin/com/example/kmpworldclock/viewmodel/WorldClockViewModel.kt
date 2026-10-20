package com.example.kmpworldclock.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpworldclock.model.Country
import com.example.kmpworldclock.util.currentTimeAt
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class WorldClockViewModel : ViewModel() {

    var showCountries by mutableStateOf(false)
        private set

    var selectedCountry by mutableStateOf<Country?>(null)
        private set

    var currentTime by mutableStateOf("Not set yet buddy!")
        private set

    private var tickingJob: Job? = null

    fun onExpandedChange(expanded: Boolean) {
        showCountries = expanded
    }

    fun onCountrySelected(country: Country) {
        selectedCountry = country

        tickingJob?.cancel()
        tickingJob = viewModelScope.launch {
            while (true) {
                currentTime = currentTimeAt(country.name, country.zone)
                delay(1000.milliseconds)
            }
        }
    }
}
