package com.example.universityschedule.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_table")
data class LessonItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val time: String,
    val building: String,
    val cabinet: String,
    val teacher: String
)