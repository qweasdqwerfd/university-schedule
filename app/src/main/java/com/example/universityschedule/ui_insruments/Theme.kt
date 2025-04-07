package com.example.universityschedule.ui_insruments

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.universityschedule.R

val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    primaryContainer = Blue,
    onPrimaryContainer = Color.White,

    secondary = PurpleGrey80,
    onSecondary = Color(0xFF332D41),
    secondaryContainer = PurpleGrey40,
    onSecondaryContainer = PurpleGrey80,

    tertiary = Pink80,
    onTertiary = Color(0xFF492532),
    tertiaryContainer = Pink40,
    onTertiaryContainer = Pink80,

    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF9DEDC),

    scrim = Color(0xFF000000).copy(alpha = 0.5f)
)

val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    primaryContainer = Blue,
    onPrimaryContainer = Color.White,

    secondary = PurpleGrey40,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    secondaryContainer = PurpleGrey80,
    onSecondaryContainer = PurpleGrey40,

    tertiary = Pink40,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    tertiaryContainer = Pink80,
    onTertiaryContainer = Pink40,

    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),

    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),

    error = Color(0xFFB3261E),
    onError = androidx.compose.ui.graphics.Color.White,
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    scrim = Color(0xFF000000).copy(alpha = 0.5f)
)
