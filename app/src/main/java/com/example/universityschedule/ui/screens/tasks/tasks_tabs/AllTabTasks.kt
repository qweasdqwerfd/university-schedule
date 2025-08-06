package com.example.universityschedule.ui.screens.tasks.tasks_tabs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.universityschedule.ui.castom_components.CardTaskPanel
import com.example.universityschedule.data.models.TaskModel

@Composable
fun AllTabTasks(tasks: List<TaskModel>) {
    LazyColumn {
        items(tasks) { task ->
            CardTaskPanel(
                title = task.title,
                description = task.description,
                dueDate = task.dueDate,
                priority = task.priority,
                lesson = task.lesson
            )
        }
    }
}