package com.example.kmpworldclock.ui

import kmpworldclock.shared.generated.resources.Res
import kmpworldclock.shared.generated.resources.ae_android
import kmpworldclock.shared.generated.resources.gb_android
import kmpworldclock.shared.generated.resources.jp_android
import kmpworldclock.shared.generated.resources.no_android
import kmpworldclock.shared.generated.resources.tz_android
import org.jetbrains.compose.resources.DrawableResource

actual fun flagImage(code: String): DrawableResource = when (code) {
    "ae" -> Res.drawable.ae_android
    "gb" -> Res.drawable.gb_android
    "jp" -> Res.drawable.jp_android
    "no" -> Res.drawable.no_android
    "tz" -> Res.drawable.tz_android
    else -> error("No flag drawable for code: $code")
}
