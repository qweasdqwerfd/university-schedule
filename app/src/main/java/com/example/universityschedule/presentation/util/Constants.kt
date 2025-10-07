package com.example.universityschedule.presentation.util

import androidx.compose.ui.graphics.Color

object Constants {
    //массив дисциплин за семестр
    val lessonsColors = mapOf(
        "NONE" to Color.Blue,
        "CALCULUS" to Color(0xFFF0A62B),
        "CALCULUS" to Color(0xFF31b947),
        "MATH" to Color(0xFF24afb5),
        "PROGRAMMING" to Color(0xFFf6c610),
        "SPORT" to Color(0xFFca2244)
    )
    val lessonsList = listOf("NONE", "CALCULUS", "PHYSICS", "MATH", "PROGRAMMING", "SPORT")

}