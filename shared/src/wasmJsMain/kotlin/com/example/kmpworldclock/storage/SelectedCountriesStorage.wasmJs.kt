package com.example.kmpworldclock.storage

import kotlinx.browser.window

private const val KEY_SELECTED_CODES = "selected_country_codes"

internal class WasmJsSelectedCountriesStorage : SelectedCountriesStorage {
    override suspend fun save(countryCodes: List<String>) {
        window.localStorage.setItem(KEY_SELECTED_CODES, countryCodes.joinToString(","))
    }

    override suspend fun load(): List<String> {
        val raw = window.localStorage.getItem(KEY_SELECTED_CODES) ?: return emptyList()
        return raw.split(",").filter { it.isNotBlank() }
    }
}

actual fun createSelectedCountriesStorage(): SelectedCountriesStorage =
    WasmJsSelectedCountriesStorage()
