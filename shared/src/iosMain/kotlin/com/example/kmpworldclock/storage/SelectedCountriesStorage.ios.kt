package com.example.kmpworldclock.storage

import platform.Foundation.NSUserDefaults

private const val KEY_SELECTED_CODES = "selected_country_codes"

internal class IosSelectedCountriesStorage : SelectedCountriesStorage {
    private val defaults = NSUserDefaults.standardUserDefaults

    override suspend fun save(countryCodes: List<String>) {
        defaults.setObject(countryCodes.joinToString(","), KEY_SELECTED_CODES)
    }

    override suspend fun load(): List<String> {
        val raw = defaults.stringForKey(KEY_SELECTED_CODES) ?: return emptyList()
        return raw.split(",").filter { it.isNotBlank() }
    }
}

actual fun createSelectedCountriesStorage(): SelectedCountriesStorage =
    IosSelectedCountriesStorage()
