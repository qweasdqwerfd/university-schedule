package com.example.universityschedule.presentation.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

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

    val formatterForLocalTime by mutableStateOf(
        DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale("ru"))
    )

    fun formatTime(timeString: String): String {
        val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = inputFormat.parse(timeString)
        return outputFormat.format(date)
    }


}