package com.example.kmpworldclock.model

import kotlinx.datetime.TimeZone
import kmpworldclock.shared.generated.resources.Res
import kmpworldclock.shared.generated.resources.tz
import kmpworldclock.shared.generated.resources.gb
import kmpworldclock.shared.generated.resources.jp
import kmpworldclock.shared.generated.resources.ae
import kmpworldclock.shared.generated.resources.no
import org.jetbrains.compose.resources.DrawableResource

data class Country(val name: String, val zone: TimeZone, val image: DrawableResource)

val defaultCountries = listOf(
    Country("Tanzania", TimeZone.of("Africa/Dar_es_Salaam"), Res.drawable.tz),
    Country("United Kingdom", TimeZone.of("Europe/London"), Res.drawable.gb),
    Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
    Country("Dubai", TimeZone.of("Asia/Dubai"), Res.drawable.ae),
    Country("Norway", TimeZone.of("Europe/Oslo"), Res.drawable.no)
)
