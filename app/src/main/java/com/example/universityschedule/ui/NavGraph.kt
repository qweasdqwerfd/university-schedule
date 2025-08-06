package com.example.universityschedule.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.universityschedule.data.TaskViewModel
import com.example.universityschedule.ui.screens.calendar.Calendar
import com.example.universityschedule.ui.screens.calendar.DayScreen
import com.example.universityschedule.ui.screens.lessons.Lessons
import com.example.universityschedule.ui.screens.tasks.NewTaskScreen
import com.example.universityschedule.ui.screens.tasks.Tasks

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    ) {

    val taskViewModel: TaskViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = "calendar") {

        composable("calendar") {
            Calendar(navHostController)
        }

        composable("lessons") {
            Lessons()
        }
        composable("addTask") {
            NewTaskScreen(
                onSave = {

                },
            )
        }


        composable("tasks") {
            Tasks(navHostController, taskViewModel)
        }



        composable(
            "day/{dayId}",
            arguments = listOf(navArgument("dayId") { type = NavType.StringType }),

            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) }


        ) { backStackEntry ->
            val dayId = backStackEntry.arguments?.getString("dayId")
            DayScreen(dayId = dayId ?: "")
        }
    }

}