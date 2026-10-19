package com.example.kmpworldclock.util

import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

fun currentTimeAt(location: String, zone: TimeZone): String {

    val timeFormat = LocalTime.Format({
        hour()
        char(':')
        minute()
        char(':')
        second()
    })

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time
    return "The time in $location is ${localTime.format(timeFormat)}"
}
