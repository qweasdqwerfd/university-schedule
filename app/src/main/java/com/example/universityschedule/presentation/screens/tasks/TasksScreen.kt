package com.example.universityschedule.presentation.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.custom_components.IconButton.FAB
import com.example.universityschedule.presentation.screens.tasks.components.tasks_tabs.ActiveTabTasks
import com.example.universityschedule.presentation.screens.tasks.components.tasks_tabs.AllTabTasks
import com.example.universityschedule.presentation.screens.tasks.components.tasks_tabs.CompletedTabTasks
import com.example.universityschedule.presentation.screens.tasks.components.tasks_tabs.TabRowPager

@Composable
fun TasksScreen(viewModel: TaskViewModel) {


    val itemsList = viewModel.sortedTasks.collectAsState()


    Column {
        TabRowPager(
            tabs = listOf("Все", "Активные", "Выполненные"),
            pages = listOf(
                { AllTabTasks(itemsList.value, viewModel) },
                { ActiveTabTasks(itemsList.value.filter { !it.check }, viewModel) },
                { CompletedTabTasks(itemsList.value.filter { it.check }, viewModel) }
            ),
        )
    }




    FAB {
        viewModel.onDialogEvent(DialogEvent.OnFABClick)
    }

}


