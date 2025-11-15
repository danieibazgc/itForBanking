package com.danieibazgc.interbank.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val InterbankColorScheme = lightColorScheme(
    primary = InterbankGreen,
    onPrimary = Color.White,
    primaryContainer = InterbankGreenDark,
    secondary = InterbankBlue,
    background = Color.White,
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    surfaceVariant = InterbankGray,
    outline = InterbankLightGray
)

@Composable
fun InterbankTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = InterbankColorScheme,
        typography = Typography,
        content = content
    )
}

