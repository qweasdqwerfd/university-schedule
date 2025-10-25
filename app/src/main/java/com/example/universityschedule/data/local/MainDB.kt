package com.example.universityschedule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.universityschedule.data.local.Converters
import com.example.universityschedule.domain.model.LessonItem
import com.example.universityschedule.domain.model.TaskItem

@Database(
    entities = [
        TaskItem::class,
        LessonItem::class
    ],

    version = 5
)
@TypeConverters(Converters::class)
abstract class MainDB : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val lessonDao: LessonDao
}