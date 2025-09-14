package com.example.universityschedule.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.universityschedule.data.dao.LessonDao
import com.example.universityschedule.data.dao.TaskDao
import com.example.universityschedule.data.entities.LessonItem
import com.example.universityschedule.data.entities.TaskItem
import com.example.universityschedule.data.view_models.contracts.Converters

@Database(
    entities = [
        TaskItem::class,
        LessonItem::class
    ],

    version = 2
)
@TypeConverters(Converters::class)
abstract class MainDB : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val lessonDao: LessonDao
}