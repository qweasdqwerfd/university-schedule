package com.example.universityschedule.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.screens.calendar.Calendar
import com.example.universityschedule.ui.screens.lessons.Lessons
import com.example.universityschedule.ui.screens.tasks.NewTaskScreen
import com.example.universityschedule.ui.screens.tasks.TaskDetailsScreen
import com.example.universityschedule.ui.screens.tasks.TasksScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    val taskViewModel: TaskViewModel = hiltViewModel()

    NavHost(navController = navHostController, startDestination = Screen.CALENDAR.route) {
        composable(Screen.CALENDAR.route) {
            Calendar(navHostController)
        }

        composable(Screen.LESSONS.route) {
            Lessons()
        }
        composable(Screen.TASKS.route) {
            TasksScreen(navHostController, taskViewModel)
        }
        composable(Screen.ADD_TASK.route) {
            NewTaskScreen(taskViewModel)
        }
        composable (Screen.TASK_DETAILS.route) {
            TaskDetailsScreen()
        }


    }
}