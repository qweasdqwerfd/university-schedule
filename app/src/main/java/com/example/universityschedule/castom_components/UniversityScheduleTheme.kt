package com.example.universityschedule.castom_components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.universityschedule.ui_insruments.DarkColorScheme
import com.example.universityschedule.ui_insruments.LightColorScheme
import com.example.universityschedule.ui_insruments.MyTypography

@Composable
fun UniversityScheduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyTypography,
//        shapes = MyShapes,
        content = content
    )
}