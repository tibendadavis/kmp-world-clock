package com.example.kmpworldclock.model

import kotlinx.datetime.TimeZone

data class Country(val name: String, val zone: TimeZone, val flagCode: String)

val defaultCountries = listOf(
    Country("Tanzania", TimeZone.of("Africa/Dar_es_Salaam"), "tz"),
    Country("United Kingdom", TimeZone.of("Europe/London"), "gb"),
    Country("Japan", TimeZone.of("Asia/Tokyo"), "jp"),
    Country("Dubai", TimeZone.of("Asia/Dubai"), "ae"),
    Country("Norway", TimeZone.of("Europe/Oslo"), "no")
)
