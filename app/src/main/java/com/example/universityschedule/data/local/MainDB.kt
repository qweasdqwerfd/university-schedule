package com.example.universityschedule.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.model.TaskItemEntity

@Database(
    entities = [
        TaskItemEntity::class,
        GroupEntity::class,
    ],

    version = 7
)
@TypeConverters(Converters::class)
abstract class MainDB : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val groupDao: GroupDao
}