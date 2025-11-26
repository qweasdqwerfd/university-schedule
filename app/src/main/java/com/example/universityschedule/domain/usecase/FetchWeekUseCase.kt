package com.example.universityschedule.domain.usecase

import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.repository.LessonsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import javax.inject.Inject

class FetchWeekUseCase @Inject constructor(
    private val repo: LessonsRepository
){
    suspend operator fun invoke(
        startOfWeek: LocalDate,
        markAsCurrent: Boolean,
        lessonsByDate: MutableStateFlow<Map<LocalDate, List<Lesson>>>,
        fetchingWeeks: MutableSet<LocalDate>,
        isLoadingCurrentWeek: MutableStateFlow<Boolean>

    ) = repo.fetchWeekIfNeeded(
        startOfWeek,
        markAsCurrent,
        lessonsByDate,
        fetchingWeeks,
        isLoadingCurrentWeek
    )
}