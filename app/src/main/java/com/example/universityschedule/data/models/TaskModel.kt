package com.example.universityschedule.data.models

data class TaskModel(
    val title: String,
    val description: String,
    val dueDate: String,
    val priority: String,
    val lesson: String,
    val isCompleted: Boolean = false // Добавьте это поле
)