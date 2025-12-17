package com.example.universityschedule.data.repository

import android.util.Log
import com.example.universityschedule.data.local.dao.LessonsDao
import com.example.universityschedule.data.local.dao.LoadedWeeksDao
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.data.local.mapping.toDomain
import com.example.universityschedule.data.remote.service.LessonsApiService
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.model.LessonEntity
import com.example.universityschedule.domain.model.LoadedWeekEntity
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.presentation.screens.calendar.components.enums.LessonType
import com.google.gson.Gson
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
    private val prefs: UserPrefsRepository,
    private val dao: LessonsDao,
    private val loadedWeeksDao: LoadedWeeksDao
) : LessonsRepository {

    override suspend fun getLessons(
        start: LocalDate,
        end: LocalDate
    ): List<Lesson> {

        val groupId = prefs.getSelectedGroupId() ?: return emptyList()
        val startOfWeek = start // тут ты всегда передаёшь начало недели

        // ✅ 1. если неделя уже загружена — ТОЛЬКО Room
        if (loadedWeeksDao.isWeekLoaded(groupId, startOfWeek)) {
            Log.d("LessonsRepo", "Week $startOfWeek loaded -> Room only")
            return dao.getLessons(groupId, start, end)
                .map { it.toDomain() }
        }

        // 🌐 2. если не загружена — идём в сеть
        Log.d("LessonsRepo", "Week $startOfWeek not loaded -> API")

        val resp = api.getLessonsByPartGroup(
            start.toString(),
            end.toString(),
            detectNowSemester(start),
            groupId
        )

        val entities = resp.lessons.flatMap { dto ->
            dto.dates.orEmpty().map { date ->
                LessonEntity(
                    lessonId = dto.id!!,
                    groupId = groupId,
                    date = LocalDate.parse(date),
                    subjectName = dto.subject_short_name!!,
                    startTime = dto.start_time,
                    endTime = dto.end_time,
                    type = mapType(dto.type),
                    locationJson = Gson().toJson(dto.rooms),
                    teacherJson = Gson().toJson(dto.employees)
                )
            }
        }

        // 💾 3. сохраняем пары
        dao.insertAll(entities)

        // 🏁 4. помечаем неделю загруженной (ДАЖЕ если пар 0)
        loadedWeeksDao.markWeekLoaded(
            LoadedWeekEntity(
                groupId = groupId,
                weekStart = startOfWeek
            )
        )

        return entities.map { it.toDomain() }
    }


    override suspend fun fetchWeekIfNeeded(
        startOfWeek: LocalDate,
        markAsCurrent: Boolean,
        lessonsByDate: MutableStateFlow<Map<LocalDate, List<Lesson>>>,
        fetchingWeeks: MutableSet<LocalDate>,
        isLoadingCurrentWeek: MutableStateFlow<Boolean>
    ) {
        if (fetchingWeeks.contains(startOfWeek)) return

        val groupId = prefs.getSelectedGroupId() ?: return

        // ✅ если неделя уже загружена — просто читаем из Room
        if (loadedWeeksDao.isWeekLoaded(groupId, startOfWeek)) {
            val endOfWeek = startOfWeek.plusDays(5)
            val lessons = dao.getLessons(groupId, startOfWeek, endOfWeek)
                .map { it.toDomain() }

            val weekDates = (0..5).map { startOfWeek.plusDays(it.toLong()) }

            val byDate = lessons
                .flatMap { lesson ->
                    lesson.dates.map { it to lesson }
                }
                .groupBy({ it.first }, { it.second })

            val newMap = lessonsByDate.value.toMutableMap()
            weekDates.forEach { d -> newMap[d] = byDate[d] ?: emptyList() }
            lessonsByDate.value = newMap.toMap()

            Log.d("CalendarVM", "Week $startOfWeek loaded from cache")
            return
        }

        // ⬇️ если не загружена — обычная логика
        fetchingWeeks.add(startOfWeek)

        withContext(Dispatchers.IO) {
            if (markAsCurrent) isLoadingCurrentWeek.value = true
            try {
                val endOfWeek = startOfWeek.plusDays(5)
                val lessons = getLessons(startOfWeek, endOfWeek)

                val weekDates = (0..5).map { startOfWeek.plusDays(it.toLong()) }

                val byDate = lessons
                    .flatMap { lesson ->
                        lesson.dates.map { it to lesson }
                    }
                    .groupBy({ it.first }, { it.second })

                val newMap = lessonsByDate.value.toMutableMap()
                weekDates.forEach { d -> newMap[d] = byDate[d] ?: emptyList() }
                lessonsByDate.value = newMap.toMap()

                Log.d("CalendarVM", "Week $startOfWeek loaded from API")
            } catch (e: Exception) {
                Log.e("CalendarVM", "Error loading week $startOfWeek", e)
            } finally {
                if (markAsCurrent) isLoadingCurrentWeek.value = false
                fetchingWeeks.remove(startOfWeek)
            }
        }
    }

    override suspend fun onGroupChanged(newGroupId: Int) {
        Log.d("LessonsRepo", "Group changed -> clearing cache for old groups")

        dao.clearOtherGroups(newGroupId)
        loadedWeeksDao.clearOtherGroups(newGroupId)
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
