package com.example.universityschedule.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.universityschedule.presentation.screens.calendar.CalendarScreen
import com.example.universityschedule.presentation.screens.lessons.LessonsScreen
import com.example.universityschedule.presentation.screens.tasks.NewTaskScreen
import com.example.universityschedule.presentation.screens.tasks.TaskDetailsScreen
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.TasksScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    val taskViewModel: TaskViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        taskViewModel.navigation.collect { screen ->
            navHostController.navigate(screen.route) {
                launchSingleTop = true
            }
        }
    }

    NavHost(navController = navHostController, startDestination = Screen.CALENDAR.route) {
        composable(Screen.CALENDAR.route) {
            CalendarScreen()
        }

        composable(Screen.LESSONS.route) {
            LessonsScreen()
        }
        composable(Screen.TASKS.route) {
            TasksScreen(navHostController, taskViewModel)
        }
        composable(Screen.ADD_TASK.route) {
            NewTaskScreen(taskViewModel)
        }
        composable(Screen.TASK_DETAILS.route) {
            TaskDetailsScreen()
        }


    }
}