package com.example.universityschedule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.universityschedule.data.local.dao.GroupDao
import com.example.universityschedule.data.local.dao.LessonsDao
import com.example.universityschedule.data.local.dao.LoadedWeeksDao
import com.example.universityschedule.data.local.dao.TaskDao
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.model.LessonEntity
import com.example.universityschedule.domain.model.LoadedWeekEntity
import com.example.universityschedule.domain.model.TaskItemEntity

@Database(
    entities = [
        TaskItemEntity::class,
        GroupEntity::class,
        LessonEntity::class,
        LoadedWeekEntity::class
    ],

    version = 10
)
@TypeConverters(Converters::class)
abstract class MainDB : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val groupDao: GroupDao
    abstract val lessonsDao: LessonsDao
    abstract val loadWeekDao: LoadedWeeksDao

}