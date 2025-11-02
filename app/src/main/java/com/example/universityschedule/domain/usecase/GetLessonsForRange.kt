package com.example.universityschedule.domain.usecase

import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.repository.LessonsRepository
import java.time.LocalDate
import javax.inject.Inject

class GetLessonsForRange @Inject constructor(
    private val repo: LessonsRepository
) {
    suspend operator fun invoke(start: LocalDate, end: LocalDate): List<Lesson> =
        repo.getLessons(start, end)
}