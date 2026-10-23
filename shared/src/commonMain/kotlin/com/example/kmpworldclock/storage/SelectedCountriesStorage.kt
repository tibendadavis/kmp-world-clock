package com.example.kmpworldclock.storage

interface SelectedCountriesStorage {
    suspend fun save(countryCodes: List<String>)
    suspend fun load(): List<String>
}

expect fun createSelectedCountriesStorage(): SelectedCountriesStorage
