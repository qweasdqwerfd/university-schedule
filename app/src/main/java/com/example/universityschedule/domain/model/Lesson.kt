package com.example.universityschedule.domain.model

import com.example.universityschedule.data.remote.dto.PublicEmployee
import com.example.universityschedule.data.remote.dto.PublicRoom
import com.example.universityschedule.presentation.screens.calendar.components.enums.LessonType
import java.time.LocalDate

data class Lesson(
    val id: Int?,
    val subjectName: String?,
    val startTime: String?,
    val endTime: String?,
    val location: List<PublicRoom>,
    val teacher: List<PublicEmployee>,
    val type: LessonType,
    val dates: List<LocalDate> // обязательное поле, которое у тебя использовалось в VM
)