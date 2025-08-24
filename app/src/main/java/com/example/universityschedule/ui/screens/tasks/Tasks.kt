package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.custom_components.CardTaskPanel
import com.example.universityschedule.ui.custom_components.IconButton.FAB

@Composable
fun Tasks(
    navHostController: NavHostController,
    viewModel: TaskViewModel = hiltViewModel()
) {

    val itemsList = viewModel.itemsList.collectAsState(emptyList())

//    Column {
//        TabRowPager(
//            tabs = listOf("All", "Active", "Completed"),
//            pages = listOf(
//                { AllTabTasks(itemsList) },  // Передаем список задач, а не ViewModel
//                { ActiveTabTasks(tasks.filter { !it.isCompleted }) },
//                { CompletedTabTasks(tasks.filter { it.isCompleted }) }
//            ),
//        )


    LazyColumn {
        items(itemsList.value) { task ->
            CardTaskPanel(task)
        }
    }


    FAB {
        viewModel.openDialogForTask()
        navHostController.navigate("addTask")
    }

}


