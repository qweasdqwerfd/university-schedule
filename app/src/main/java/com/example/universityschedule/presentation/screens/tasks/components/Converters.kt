package com.example.universityschedule.presentation.screens.tasks.components

import androidx.room.TypeConverter
import com.example.universityschedule.presentation.screens.tasks.LessonChip
import com.example.universityschedule.presentation.screens.tasks.Priority

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
    fun fromLesson(lesson: LessonChip): String {
        return lesson.name
    }

    @TypeConverter
    fun toLesson(lesson: String): LessonChip {
        return LessonChip.valueOf(lesson)
    }
}

fun Priority.toDisplayName() = when (this) {
    Priority.LOW -> "Low"
    Priority.MEDIUM -> "Medium"
    Priority.HIGH -> "High"
}

fun LessonChip.toDisplayName() = when (this) {
    LessonChip.NONE -> "None"
    LessonChip.CALCULUS -> "Calculus"
    LessonChip.PHYSICS -> "Physics"
    LessonChip.MATH -> "Math"
    LessonChip.PROGRAMMING -> "Programming"
    LessonChip.SPORT -> "Sport"
    LessonChip.CHEMISTRY -> "Chemistry" // Добавьте при необходимости
}