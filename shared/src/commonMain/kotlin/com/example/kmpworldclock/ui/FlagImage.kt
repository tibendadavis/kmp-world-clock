package com.example.kmpworldclock.ui

import kmpworldclock.shared.generated.resources.Res
import kmpworldclock.shared.generated.resources.ae
import kmpworldclock.shared.generated.resources.gb
import kmpworldclock.shared.generated.resources.jp
import kmpworldclock.shared.generated.resources.no
import kmpworldclock.shared.generated.resources.tz
import org.jetbrains.compose.resources.DrawableResource

/**
 * Android can't rasterize raw SVG, so it needs its own vector-drawable
 * copies of the flags. Every other platform can use the shared SVGs
 * directly. [flagImage] is the expect/actual seam: same call site in
 * common UI code, different resource per platform.
 */
expect fun flagImage(code: String): DrawableResource

internal fun svgFlagImage(code: String): DrawableResource = when (code) {
    "ae" -> Res.drawable.ae
    "gb" -> Res.drawable.gb
    "jp" -> Res.drawable.jp
    "no" -> Res.drawable.no
    "tz" -> Res.drawable.tz
    else -> error("No flag SVG for code: $code")
}
