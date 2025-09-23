package com.example.universityschedule.presentation.screens.tasks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.custom_components.IconButton.FAB
import com.example.universityschedule.presentation.screens.tasks.components.CardTaskPanel

@Composable
fun TasksScreen(
    viewModel: TaskViewModel,
    navHostController: NavHostController
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
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(itemsList.value) {
            CardTaskPanel(
                item = it,
                onEvent = { event -> viewModel.onDialogEvent(event) },
            )
        }
    }


    FAB {
        viewModel.onDialogEvent(DialogEvent.OnFABClick)
    }

}


