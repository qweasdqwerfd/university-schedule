package com.example.universityschedule.presentation.screens.calendar

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.remote.api.RetrofitClient
import com.example.universityschedule.data.remote.dto.PublicPartGroup
import com.example.universityschedule.data.remote.dto.PublicStaticLesson
import com.example.universityschedule.data.remote.response.LessonsResponse
import com.example.universityschedule.data.remote.response.PaginatedResponse
import com.example.universityschedule.data.remote.service.Semester
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.usecase.GetLessonsForRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getLessonsForRange: GetLessonsForRange
) : ViewModel() {

    private val lessonsApi = RetrofitClient.lessonsApiService
    private val dictApi = RetrofitClient.dictApiService


    private val _lessons = mutableStateOf<List<PublicStaticLesson>?>(
        null
    )
    val lessons: MutableState<List<PublicStaticLesson>?> get() = _lessons

    private val _groups = mutableStateOf<PaginatedResponse<PublicPartGroup>>(
        PaginatedResponse(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )
    )

    val groups: MutableState<PaginatedResponse<PublicPartGroup>> get() = _groups


    // кэш
    private val _lessonsByDate = MutableStateFlow<Map<LocalDate, List<Lesson>>>(emptyMap())
    val lessonsByDate: StateFlow<Map<LocalDate, List<Lesson>>> = _lessonsByDate.asStateFlow()

    private val fetchingWeeks = mutableSetOf<LocalDate>()

    fun onPageChanged(pageDate: LocalDate) {
        val startOfWeek = pageDate.with(DayOfWeek.MONDAY)
        fetchWeekIfNeeded(startOfWeek)
        fetchWeekIfNeeded(startOfWeek.minusWeeks(1))
        fetchWeekIfNeeded(startOfWeek.plusWeeks(1))
    }

    private fun fetchWeekIfNeeded(startOfWeek: LocalDate) {
        if (fetchingWeeks.contains(startOfWeek)) return

        val weekDates = (0..5).map { startOfWeek.plusDays(it.toLong()) }
        if (weekDates.all { _lessonsByDate.value.containsKey(it) }) return

        fetchingWeeks.add(startOfWeek)
        viewModelScope.launch {
            try {
                val endOfWeek = startOfWeek.plusDays(5)
                val lessons = getLessonsForRange(startOfWeek, endOfWeek) // тут чистая логика
                // распихиваем уроки по датам
                val byDate = lessons
                    .flatMap { lesson ->
                        lesson.dates
                            .filter { d ->
                                d.dayOfWeek != DayOfWeek.SUNDAY && !d.isBefore(
                                    startOfWeek
                                ) && !d.isAfter(endOfWeek)
                            }
                            .map { date -> date to lesson }
                    }
                    .groupBy({ it.first }, { it.second })

                val newMap = _lessonsByDate.value.toMutableMap()
                weekDates.forEach { d -> newMap[d] = byDate[d] ?: emptyList() }
                _lessonsByDate.value = newMap.toMap()

                Log.d("qwe", "${_lessonsByDate.value}")

            } catch (e: Exception) {
                Log.d("qwe", "${e.message}")
            } finally {
                fetchingWeeks.remove(startOfWeek)
            }
        }
    }

    fun lessonsForDate(date: LocalDate) = lessonsByDate.value[date] ?: emptyList()


    fun getLessonsByPartGroup() {
        viewModelScope.launch {
            try {
                val response = lessonsApi.getLessonsByPartGroup(
                    startDate = "2025-09-01",
                    endDate = "2026-01-31",
                    semester = 0,
                    id = 969
                )

                val todayLessons =
                    response.lessons.filter { it.dates?.contains("2025-10-27") == true }

                todayLessons.forEach { lesson ->
//                    Log.d("qwe", "Пара: ${lesson.subject_name} в ${lesson.start_time}")
                    _lessons.value = listOf(lesson)
//                    Log.d("qwe",_lessons.value.toString())
                }


            } catch (e: Exception) {
                Log.e("qwe", "Error loading lessons", e)
            }
        }
    }

    fun findTargetGroup() {
        viewModelScope.launch {
            var currentPage = 1
            var foundGroup: PublicPartGroup? = null

            while (foundGroup == null) {
                try {
                    val response = dictApi.getPartGroups(page = currentPage)
                    Log.d("qwe", "Page $currentPage groups: ${response.results.map { it.name }}")

                    foundGroup = response.results.find { it.name == "ПВ-242(1)" }

                    if (foundGroup != null) {
                        Log.d("qwe", "FOUND on page $currentPage: $foundGroup")
                        _groups.value = response
                        break
                    }

                    if (response.next == null) {
                        Log.d("qwe", "Group 'ПВ-242' not found in all pages")
                        break
                    }

                    currentPage++
                } catch (e: Exception) {
                    Log.e("qwe", "Error loading page $currentPage", e)
                    break
                }
            }
        }
    }


}