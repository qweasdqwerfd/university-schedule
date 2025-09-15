package com.example.universityschedule.presentation.navigation

sealed class Screen(val route: String) {
    object CALENDAR : Screen("calendar")
    object LESSONS : Screen("lessons")
    object TASKS : Screen("tasks")
    object ADD_TASK : Screen("add_task")
    object TASK_DETAILS : Screen("task_details")
    object POP_BACK : Screen("pop_back")
}