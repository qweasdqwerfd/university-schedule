package com.example.universityschedule.domain.repository

import com.example.universityschedule.domain.model.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

interface LessonsRepository {
    suspend fun getLessons(start: LocalDate, end: LocalDate): List<Lesson>

    suspend fun fetchWeekIfNeeded(
        startOfWeek: LocalDate,
        markAsCurrent: Boolean,
        lessonsByDate: MutableStateFlow<Map<LocalDate, List<Lesson>>>,
        fetchingWeeks: MutableSet<LocalDate>,
        isLoadingCurrentWeek: MutableStateFlow<Boolean>
    )

    suspend fun onGroupChanged(newGroupId: Int)
}
