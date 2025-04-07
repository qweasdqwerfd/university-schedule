package com.example.universityschedule.ui_insruments

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Font sizes
private val FONT_SIZE_EXTRA_SMALL = 10.sp
private val FONT_SIZE_SMALL = 12.sp
private val FONT_SIZE_MEDIUM = 14.sp
private val FONT_SIZE_LARGE = 16.sp
private val FONT_SIZE_EXTRA_LARGE = 20.sp
private val FONT_SIZE_HEADLINE = 24.sp
private val FONT_SIZE_DISPLAY = 32.sp

// Line heights
private val LINE_HEIGHT_SMALL = 16.sp
private val LINE_HEIGHT_MEDIUM = 20.sp
private val LINE_HEIGHT_LARGE = 24.sp
private val LINE_HEIGHT_EXTRA_LARGE = 28.sp

// Letter spacing
private val LETTER_SPACING_SMALL = 0.25.sp
private val LETTER_SPACING_MEDIUM = 0.5.sp
private val LETTER_SPACING_LARGE = 1.sp

// Set of Material typography styles to start with
val MyTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = FONT_SIZE_DISPLAY,
        lineHeight = LINE_HEIGHT_EXTRA_LARGE,
        letterSpacing = LETTER_SPACING_SMALL
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FONT_SIZE_HEADLINE,
        lineHeight = LINE_HEIGHT_EXTRA_LARGE,
        letterSpacing = LETTER_SPACING_SMALL
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FONT_SIZE_EXTRA_LARGE,
        lineHeight = LINE_HEIGHT_LARGE,
        letterSpacing = LETTER_SPACING_SMALL
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FONT_SIZE_LARGE,
        lineHeight = LINE_HEIGHT_LARGE,
        letterSpacing = LETTER_SPACING_MEDIUM
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FONT_SIZE_MEDIUM,
        lineHeight = LINE_HEIGHT_MEDIUM,
        letterSpacing = LETTER_SPACING_MEDIUM
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FONT_SIZE_SMALL,
        lineHeight = LINE_HEIGHT_SMALL,
        letterSpacing = LETTER_SPACING_MEDIUM
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = FONT_SIZE_LARGE,
        lineHeight = LINE_HEIGHT_LARGE,
        letterSpacing = LETTER_SPACING_MEDIUM
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = FONT_SIZE_MEDIUM,
        lineHeight = LINE_HEIGHT_MEDIUM,
        letterSpacing = LETTER_SPACING_MEDIUM
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = FONT_SIZE_SMALL,
        lineHeight = LINE_HEIGHT_SMALL,
        letterSpacing = LETTER_SPACING_LARGE
    ),
//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = FONT_SIZE_EXTRA_SMALL,
//        lineHeight = LINE_HEIGHT_SMALL,
//        letterSpacing = LETTER_SPACING_SMALL
//    )
)