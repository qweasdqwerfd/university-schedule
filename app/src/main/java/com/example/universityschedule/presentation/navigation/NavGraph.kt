package com.example.universityschedule.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.universityschedule.presentation.screens.calendar.CalendarScreen
import com.example.universityschedule.presentation.screens.lessons.LessonsScreen
import com.example.universityschedule.presentation.screens.tasks.NewTaskScreen
import com.example.universityschedule.presentation.screens.tasks.TaskDetailsScreen
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.TasksScreen
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsViewModel
import javax.inject.Inject

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {

    val navGraphViewModel: NavGraphViewModel = hiltViewModel()
    val navigationManager = navGraphViewModel.navigationManager

    val navCommand by navigationManager.commands.collectAsState(initial = null)

    LaunchedEffect(navCommand) {
        navCommand?.let {
            when (it) {
                is NavigationCommand.Back -> navHostController.popBackStack()
                is NavigationCommand.To -> navHostController.navigate(it.route)
            }
        }
    }


    val taskViewModel: TaskViewModel = hiltViewModel()

    NavHost(navController = navHostController, startDestination = Screen.TASKS.route) {
        composable(Screen.CALENDAR.route) {
            CalendarScreen()
        }

        composable(Screen.LESSONS.route) {
            LessonsScreen()
        }
        composable(Screen.TASKS.route) {
            TasksScreen(
                taskViewModel,
                navHostController = navHostController,
            )
        }
        composable(Screen.ADD_TASK.route) {
            NewTaskScreen(taskViewModel)
        }
        composable(
            route = Screen.TASK_DETAILS.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->

            val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable

            val taskDetailsViewModel: TaskDetailsViewModel = hiltViewModel()


            TaskDetailsScreen(
                taskId = taskId,
                viewModel = taskDetailsViewModel
            )
        }


    }
}