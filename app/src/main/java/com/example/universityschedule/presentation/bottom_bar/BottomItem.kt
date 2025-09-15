package com.example.universityschedule.presentation.bottom_bar

import com.example.universityschedule.R
import com.example.universityschedule.presentation.navigation.Screen

sealed class BottomItem(
    val title: String,
    val icon: Int,
    val route: String
) {

    object UniversitySchedule: BottomItem("University Schedule", R.drawable.calendar, Screen.CALENDAR.route)
    object Tasks: BottomItem("Tasks", R.drawable.tasks, Screen.TASKS.route)
    object Lessons: BottomItem("lessons", R.drawable.lessons, Screen.LESSONS.route)
}