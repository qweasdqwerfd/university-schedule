package com.example.universityschedule.data.local.mapping

import com.example.universityschedule.data.remote.dto.PublicEmployee
import com.example.universityschedule.data.remote.dto.PublicRoom
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.model.LessonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun LessonEntity.toDomain(): Lesson {
    val gson = Gson()

    val roomsType = object : TypeToken<List<PublicRoom>>() {}.type
    val teachersType = object : TypeToken<List<PublicEmployee>>() {}.type

    return Lesson(
        id = lessonId,
        subjectName = subjectName,
        startTime = startTime,
        endTime = endTime,
        type = type,
        dates = listOf(date),
        location = gson.fromJson(locationJson, roomsType),
        teacher = gson.fromJson(teacherJson, teachersType)
    )
}
