package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.universityschedule.ui.castom_components.CardTaskPanel
import com.example.universityschedule.ui.castom_components.IconButton.AddTaskButton
import com.example.universityschedule.data.TaskViewModel
import com.example.universityschedule.ui.screens.TabRowPager
import com.example.universityschedule.ui.screens.tasks.tasks_tabs.ActiveTabTasks
import com.example.universityschedule.ui.screens.tasks.tasks_tabs.AllTabTasks
import com.example.universityschedule.ui.screens.tasks.tasks_tabs.CompletedTabTasks

@Composable
fun Tasks(
    navHostController: NavHostController,
    viewModel: TaskViewModel
) {
    // Получаем текущий список задач из ViewModel
    val tasks = viewModel.tasks

    Column {
        TabRowPager(
            tabs = listOf("All", "Active", "Completed"),
            pages = listOf(
                { AllTabTasks(tasks) },  // Передаем список задач, а не ViewModel
                { ActiveTabTasks(tasks.filter { !it.isCompleted }) },
                { CompletedTabTasks(tasks.filter { it.isCompleted }) }
            ),
        )

        LazyColumn {
            items(tasks) { task ->  // Используем tasks вместо viewModel напрямую
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

    AddTaskButton {
        navHostController.navigate("addTask")
    }
}

