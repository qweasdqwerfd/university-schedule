package com.example.universityschedule.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.data.view_models.contracts.controllers.Lesson
import com.example.universityschedule.data.view_models.contracts.controllers.Priority

@Entity(tableName = "task_table")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val priority: Priority,
    val lessons: Lesson,
    val title: String,
    val description: String,
    val dueDate: String
)