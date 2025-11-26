package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.presentation.common.dialog_controller.LessonChip
import com.example.universityschedule.presentation.common.dialog_controller.Priority
import java.time.LocalDateTime

@Entity(tableName = "task_table")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val priority: Priority,
    val lessons: LessonChip,
    val title: String,
    val description: String,
    val dueDate: LocalDateTime,
    val check: Boolean
)