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
