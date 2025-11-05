package com.example.universityschedule.data.repository

import com.example.universityschedule.data.remote.service.LessonsApiService
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.presentation.screens.calendar.components.enums.LessonType
import java.time.LocalDate
import javax.inject.Inject

class LessonsRepositoryImpl @Inject constructor(
    private val api: LessonsApiService
) : LessonsRepository {

    override suspend fun getLessons(start: LocalDate, end: LocalDate): List<Lesson> {
        val resp = api.getLessonsByPartGroup(
            startDate = start.toString(),
            endDate = end.toString(),
            semester = 0,
            id = 969
        )

        return resp.lessons.map { dto ->
            Lesson(
                id = dto.id,
                subjectName = dto.subject_short_name,
                startTime = dto.start_time,
                endTime = dto.end_time,
                type = mapType(dto.type),
                dates = dto.dates?.map { LocalDate.parse(it) } ?: emptyList(),
                location = dto.rooms,
                teacher = dto.employees
            )
        }
    }
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
