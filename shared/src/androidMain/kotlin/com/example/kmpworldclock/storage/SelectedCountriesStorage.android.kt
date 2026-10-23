package com.example.kmpworldclock.storage

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

private const val PREFS_NAME = "secure_world_clock_prefs"
private const val KEY_SELECTED_CODES = "selected_country_codes"

internal class AndroidSelectedCountriesStorage : SelectedCountriesStorage {

    private val prefs by lazy {
        val context = AndroidContextHolder.appContext
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    override suspend fun save(countryCodes: List<String>) {
        prefs.edit().putString(KEY_SELECTED_CODES, countryCodes.joinToString(",")).apply()
    }

    override suspend fun load(): List<String> {
        val raw = prefs.getString(KEY_SELECTED_CODES, null) ?: return emptyList()
        return raw.split(",").filter { it.isNotBlank() }
    }
}

actual fun createSelectedCountriesStorage(): SelectedCountriesStorage = AndroidSelectedCountriesStorage()
