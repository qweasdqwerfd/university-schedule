package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.custom_components.IconButton.FAB

@Composable
fun TasksScreen(
    navHostController: NavHostController,
    viewModel: TaskViewModel
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


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
        ,
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(itemsList.value) { task ->
            CardTaskPanel(task)
        }
    }


    FAB {
        navHostController.navigate("addTask")
    }

}


