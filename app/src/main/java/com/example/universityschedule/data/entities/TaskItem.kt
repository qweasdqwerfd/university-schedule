package com.example.universityschedule.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskItem(
    @PrimaryKey
    val id: Int? = null,
    val priority: String,
    val lessons: String,
    val title: String,
    val description: String,
    val dueDate: String
)