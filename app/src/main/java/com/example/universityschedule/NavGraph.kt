package com.example.universityschedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.universityschedule.screens.calendar.Calendar
import com.example.universityschedule.screens.lessons.Lessons
import com.example.universityschedule.screens.tasks.Tasks
import com.example.universityschedule.screens.calendar.DayScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,

    ) {

    NavHost(navController = navHostController, startDestination = "calendar") {

        composable("calendar") {
            Calendar(navHostController)
        }

        composable("tasks") {
            Tasks()
        }
        composable("lessons") {
            Lessons()
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