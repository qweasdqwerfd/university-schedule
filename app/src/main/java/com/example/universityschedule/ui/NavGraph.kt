package com.example.universityschedule.ui

//import com.example.universityschedule.ui.screens.calendar.DayScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.screens.lessons.Lessons
import com.example.universityschedule.ui.screens.tasks.NewTaskScreen
import com.example.universityschedule.ui.screens.tasks.Tasks

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
) {

    val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModel.factory)


    NavHost(navController = navHostController, startDestination = "tasks") {

//        composable("calendar") {
//            Calendar(navHostController)
//        }

        composable("lessons") {
            Lessons()
        }
        composable("addTask") {
            NewTaskScreen(navHostController)
        }


        composable("tasks") {
            Tasks(navHostController, taskViewModel)
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