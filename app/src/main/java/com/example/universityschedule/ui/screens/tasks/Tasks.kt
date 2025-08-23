package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.universityschedule.ui.custom_components.CardTaskPanel
import com.example.universityschedule.ui.custom_components.IconButton.AddTaskButton
import com.example.universityschedule.data.view_models.TaskViewModel

@Composable
fun Tasks(
    navHostController: NavHostController,
) {


//    Column {


//        TabRowPager(
//            tabs = listOf("All", "Active", "Completed"),
//            pages = listOf(
//                { AllTabTasks(itemsList) },  // Передаем список задач, а не ViewModel
//                { ActiveTabTasks(tasks.filter { !it.isCompleted }) },
//                { CompletedTabTasks(tasks.filter { it.isCompleted }) }
//            ),
//        )


//        LazyColumn {
//            items(itemsList) { task ->
//                CardTaskPanel(
//                    title = task.title,
//                    description = task.description,
//                    dueDate = task.dueDate,
//                    priority = task.priority,
//                    lesson = task.lessons
//                )
//            }
//        }
//    }
//
//    AddTaskButton {
//        navHostController.navigate("addTask")
//    }
}

