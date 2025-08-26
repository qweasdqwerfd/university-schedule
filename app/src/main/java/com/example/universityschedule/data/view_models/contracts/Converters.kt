package com.example.universityschedule.data.view_models.contracts

import androidx.room.TypeConverter
import com.example.universityschedule.data.view_models.contracts.controllers.Lesson
import com.example.universityschedule.data.view_models.contracts.controllers.Priority

class Converters {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

    @TypeConverter
    fun fromLesson(lesson: Lesson): String {
        return lesson.name
    }

    @TypeConverter
    fun toLesson(lesson: String): Lesson {
        return Lesson.valueOf(lesson)
    }
}

fun Priority.toDisplayName() = when (this) {
    Priority.LOW -> "Low"
    Priority.MEDIUM -> "Medium"
    Priority.HIGH -> "High"
}

fun Lesson.toDisplayName() = when (this) {
    Lesson.NONE -> "None"
    Lesson.CALCULUS -> "Calculus"
    Lesson.PHYSICS -> "Physics"
    Lesson.MATH -> "Math"
    Lesson.PROGRAMMING -> "Programming"
    Lesson.SPORT -> "Sport"
    Lesson.CHEMISTRY -> "Chemistry" // Добавьте при необходимости
}