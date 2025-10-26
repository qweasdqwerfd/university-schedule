package com.example.universityschedule.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.universityschedule.presentation.screens.calendar.CalendarScreen
import com.example.universityschedule.presentation.screens.tasks.NewTaskScreen
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.TasksScreen
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsScreen
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    snackBarHostState: SnackbarHostState,
) {

    val navGraphViewModel: NavGraphViewModel = hiltViewModel()
    val uiManager = navGraphViewModel.UIManager

    LaunchedEffect(Unit) {
        uiManager.commands.collect { command ->                                                     //collectLatest fix bug
            when (command) {
                is NavigationCommand.Back -> navHostController.popBackStack()
                is NavigationCommand.To -> navHostController.navigate(command.route)
            }
        }
    }

    LaunchedEffect(uiManager.snackBarCommands) {
        uiManager.snackBarCommands.collect { data ->
            snackBarHostState.showSnackbar(
                message = data.message,
                actionLabel = data.type.name,
                duration = data.duration
            )
        }
    }


    val taskViewModel: TaskViewModel = hiltViewModel()

    NavHost(navController = navHostController, startDestination = Screen.CALENDAR.route) {
        composable(Screen.CALENDAR.route) {
            CalendarScreen()
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