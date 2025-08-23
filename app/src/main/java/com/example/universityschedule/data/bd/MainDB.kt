package com.example.universityschedule.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.universityschedule.data.dao.LessonDao
import com.example.universityschedule.data.dao.TaskDao
import com.example.universityschedule.data.entities.LessonItem
import com.example.universityschedule.data.entities.TaskItem

@Database(
    entities = [
        TaskItem::class,
        LessonItem::class
    ],

    version = 1
)

abstract class MainDB : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val lessonDao: LessonDao
}