package com.example.kmpworldclock.storage

import java.util.prefs.Preferences

private const val KEY_SELECTED_CODES = "selected_country_codes"

internal class DesktopSelectedCountriesStorage : SelectedCountriesStorage {
    private val prefs = Preferences.userRoot().node("com.example.kmpworldclock")

    override suspend fun save(countryCodes: List<String>) {
        prefs.put(KEY_SELECTED_CODES, countryCodes.joinToString(","))
    }

    override suspend fun load(): List<String> {
        val raw = prefs.get(KEY_SELECTED_CODES, null) ?: return emptyList()
        return raw.split(",").filter { it.isNotBlank() }
    }
}

actual fun createSelectedCountriesStorage(): SelectedCountriesStorage =
    DesktopSelectedCountriesStorage()
