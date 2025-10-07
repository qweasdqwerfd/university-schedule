package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.LessonChip
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Priority

@Entity(tableName = "task_table")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val priority: Priority,
    val lessons: LessonChip,
    val title: String,
    val description: String,
    val dueDate: String,
    val check: Boolean
)