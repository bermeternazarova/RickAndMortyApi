package com.example.rickandmortyapi.presentation.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val violet: Color = Color.Unspecified,
    val darkYellow: Color = Color.Unspecified
)
val LocalCustomColorsPalette = staticCompositionLocalOf { ColorPalette() }

val OnLightCustomColorsPalette = ColorPalette(
    violet = Color(color = 0xFF6665C8),
    darkYellow = Color(color = 0xFFDA8945)
)

val OnDarkCustomColorsPalette = ColorPalette(
    violet = Color(color = 0xFF6665C8),
    darkYellow = Color(color = 0xFFDA8945)
)