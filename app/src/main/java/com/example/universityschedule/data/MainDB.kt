package com.example.universityschedule.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.universityschedule.data.dao.TaskDao
import com.example.universityschedule.data.entities.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],

    version = 1
)

abstract class MainDB: RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        fun createDataBase(context: App): MainDB {
            return Room.databaseBuilder(
                context = context,
                klass = MainDB::class.java,
                name = "test.db"
            ).build()
        }
    }


}