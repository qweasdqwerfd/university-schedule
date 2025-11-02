package com.example.universityschedule.domain.repository

import com.example.universityschedule.domain.model.Lesson
import java.time.LocalDate

interface LessonsRepository {
    suspend fun getLessons(start: LocalDate, end: LocalDate): List<Lesson>
}