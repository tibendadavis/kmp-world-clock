package com.example.kmpworldclock

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform