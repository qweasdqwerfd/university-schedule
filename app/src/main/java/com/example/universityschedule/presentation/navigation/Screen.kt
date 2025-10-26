package com.example.universityschedule.presentation.navigation

sealed class Screen(val route: String) {
    object CALENDAR : Screen("calendar")
    object TASKS : Screen("tasks")
    object ADD_TASK : Screen("add_task")
    object TASK_DETAILS: Screen("task_details/{taskId}") {
        fun createRoute(taskId: Int) = "task_details/$taskId?ts=${System.currentTimeMillis()}"
    }
}