package com.example.universityschedule.data.repository

import android.util.Log
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.data.remote.service.LessonsApiService
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.presentation.screens.calendar.components.enums.LessonType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject
import kotlin.collections.forEach

class LessonsRepositoryImpl @Inject constructor(
    private val api: LessonsApiService,
    private val prefs: UserPrefsRepository
) : LessonsRepository {

    override suspend fun getLessons(start: LocalDate, end: LocalDate): List<Lesson> {
        try {
            val groupId = prefs.getSelectedGroupId()
            Log.d("LessonsRepo", "getLessons for groupId = $groupId, start=$start end=$end semester: ${detectNowSemester(start)}")

            if (groupId == null) {
                Log.w("LessonsRepo", "groupId == null -> returning empty list")
                return emptyList()
            }

            Log.d("DBG", "LessonsRepo: about to call API with groupId=$groupId")


            val resp = api.getLessonsByPartGroup(
                startDate = start.toString(),
                endDate = end.toString(),
                semester = detectNowSemester(start),
                id = groupId //
            )

            Log.d("LessonsRepo", "semestr: ${detectNowSemester(start)}, groupId: $groupId")

            Log.d("LessonsRepo", "API success: lessonsCount=${resp.lessons.size}")

            return resp.lessons.map { dto ->
                val parsedDates = try {
                    dto.dates?.map { LocalDate.parse(it) } ?: emptyList()
                } catch (e: Exception) {
                    Log.e("LessonsRepo", "Date parse error for dto.id=${dto.id}, rawDates=${dto.dates}", e)
                    emptyList<LocalDate>()
                }

                Lesson(
                    id = dto.id,
                    subjectName = dto.subject_short_name,
                    startTime = dto.start_time,
                    endTime = dto.end_time,
                    type = mapType(dto.type),
                    dates = parsedDates,
                    location = dto.rooms,
                    teacher = dto.employees
                )
            }
        } catch (e: Exception) {
            Log.e("LessonsRepo", "getLessons failed: ${e.message}", e)
            return emptyList()
        }
    }


    override suspend fun fetchWeekIfNeeded(
        startOfWeek: LocalDate,
        markAsCurrent: Boolean,
        lessonsByDate: MutableStateFlow<Map<LocalDate, List<Lesson>>>,
        fetchingWeeks: MutableSet<LocalDate>,
        isLoadingCurrentWeek: MutableStateFlow<Boolean>
    ) {
        if (fetchingWeeks.contains(startOfWeek)) return

        val weekDates = (0..5).map { startOfWeek.plusDays(it.toLong()) } // Mon..Sat
        if (weekDates.all { lessonsByDate.value.containsKey(it) }) return

        fetchingWeeks.add(startOfWeek)

        withContext(Dispatchers.IO) {
            if (markAsCurrent) isLoadingCurrentWeek.value = true
            try {
                val endOfWeek = startOfWeek.plusDays(5)
                val lessons = getLessons(startOfWeek, endOfWeek)

                val byDate = lessons
                    .flatMap { lesson ->
                        lesson.dates
                            .filter { d -> d.dayOfWeek != DayOfWeek.SUNDAY && !d.isBefore(startOfWeek) && !d.isAfter(endOfWeek) }
                            .map { date -> date to lesson }
                    }
                    .groupBy({ it.first }, { it.second })

                val newMap = lessonsByDate.value.toMutableMap()
                weekDates.forEach { d -> newMap[d] = byDate[d] ?: emptyList() }
                lessonsByDate.value = newMap.toMap()

                Log.d("CalendarVM", "Loaded week $startOfWeek -> ${byDate.values.sumBy { it.size }} lessons")
            } catch (e: Exception) {
                Log.e("CalendarVM", "Error loading week $startOfWeek: ${e.message}", e)
            } finally {
                if (markAsCurrent) isLoadingCurrentWeek.value = false
                fetchingWeeks.remove(startOfWeek)
            }
        }

    }
}

private fun detectNowSemester(date: LocalDate): Int {
    return if (date.month in listOf(
            Month.SEPTEMBER,
            Month.OCTOBER,
            Month.NOVEMBER,
            Month.DECEMBER,
            Month.JANUARY
        )
    ) 0 else 1

}

fun mapType(value: String?): LessonType {
    return when (value?.trim()) {
        "1", "Лекция" -> LessonType.Lecture
        "2", "Лабораторная" -> LessonType.Lab
        "3", "Практика" -> LessonType.Practice
        "4", "Консультация" -> LessonType.Consultation
        "5", "РГЗ" -> LessonType.RGZ
        "6", "ИДЗ" -> LessonType.IDZ
        "7", "Контроль самостоятельной работы" -> LessonType.SupervisionOfSelfEmployment
        "8", "Зачет" -> LessonType.SetOff
        "9", "Дифференцированный зачет" -> LessonType.DifferentialSet
        "10", "Экзамен" -> LessonType.Exam
        "11", "Курсовая работа" -> LessonType.Coursework
        "12", "Курсовой проект" -> LessonType.CourseProject
        "13", "Аттестация" -> LessonType.Attestation
        "14", "Промежуточная аттестация" -> LessonType.InterimCertification
        "15", "Текущая консультация" -> LessonType.CurrentConsultation
        else -> LessonType.Lecture
    }
}
