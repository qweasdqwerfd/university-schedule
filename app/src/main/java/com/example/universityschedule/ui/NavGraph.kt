package com.example.universityschedule.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.universityschedule.ui.screens.lessons.Lessons
import com.example.universityschedule.ui.screens.tasks.NewTaskScreen
import com.example.universityschedule.ui.screens.tasks.Tasks

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {



    NavHost(navController = navHostController, startDestination = "tasks") {

//        composable("calendar") {
//            Calendar(navHostController)
//        }

        composable("lessons") {
            Lessons()
        }
        composable("addTask") {
            NewTaskScreen(
                navHostController = navHostController
            )
        }


        composable("tasks") {
            Tasks(navHostController)
        }


//        composable(
//            "day/{dayId}",
//            arguments = listOf(navArgument("dayId") { type = NavType.StringType }),
//
//            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
//            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) }
//
//
//        ) { backStackEntry ->
//            val dayId = backStackEntry.arguments?.getString("dayId")
//            DayScreen(dayId = dayId ?: "")
//        }
    }

}