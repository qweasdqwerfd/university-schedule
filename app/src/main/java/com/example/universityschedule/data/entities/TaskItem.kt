package com.example.universityschedule.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.data.view_models.contracts.controllers.LessonChip
import com.example.universityschedule.data.view_models.contracts.controllers.Priority

@Entity(tableName = "task_table")
data class TaskItem(
    @PrimaryKey
    val id: Int? = null,
    val priority: Priority,
    val lessons: LessonChip,
    val title: String,
    val description: String,
    val dueDate: String
)