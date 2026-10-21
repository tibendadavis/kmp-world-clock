package com.example.kmpworldclock.util

import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

private val timeFormat = LocalTime.Format {
    hour()
    char(':')
    minute()
    char(':')
    second()
}

fun formatTime(now: Instant, zone: TimeZone): String =
    now.toLocalDateTime(zone).time.format(timeFormat)
