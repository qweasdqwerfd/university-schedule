package com.example.universityschedule.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,
    val priority: String,
    val lessons: String,
    val title: String,
    val description: String,
    val dueDate: String

)