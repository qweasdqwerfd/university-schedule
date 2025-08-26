package com.example.universityschedule.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.screens.calendar.Calendar
import com.example.universityschedule.ui.screens.lessons.Lessons
import com.example.universityschedule.ui.screens.tasks.NewTaskScreen
import com.example.universityschedule.ui.screens.tasks.TasksScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    val taskViewModel: TaskViewModel = hiltViewModel()

    NavHost(navController = navHostController, startDestination = "tasks") {
        composable("calendar") {
            Calendar(navHostController)
        }

        composable("lessons") {
            Lessons()
        }
        composable("tasks") {
            TasksScreen(navHostController, taskViewModel)
        }
        composable("addTask") {
            NewTaskScreen(taskViewModel)
        }


    }
}