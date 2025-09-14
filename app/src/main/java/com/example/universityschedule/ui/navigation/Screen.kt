package com.example.universityschedule.ui.navigation

sealed class Screen {
    object CALENDAR : Screen()
    object LESSONS: Screen()
    object TASKS: Screen()
    object ADD_TASK: Screen()
    object TASK_DETAILS: Screen()
    val route: String
        get() = this::class.simpleName!!.lowercase()
}

