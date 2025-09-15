package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.presentation.screens.tasks.LessonChip
import com.example.universityschedule.presentation.screens.tasks.Priority

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