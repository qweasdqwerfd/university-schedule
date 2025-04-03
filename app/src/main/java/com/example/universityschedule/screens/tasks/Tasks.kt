package com.example.universityschedule.screens.tasks

import androidx.compose.runtime.Composable
import com.example.universityschedule.castom_components.AddTaskButton
import com.example.universityschedule.screens.TabRowPager
import com.example.universityschedule.screens.tasks.tasks_tabs.ActiveTabTasks
import com.example.universityschedule.screens.tasks.tasks_tabs.AllTabTasks
import com.example.universityschedule.screens.tasks.tasks_tabs.CompletedTabTasks

@Composable
fun Tasks() {

    TabRowPager(
        tabs = listOf("All", "Active", "Completed"),
        pages = listOf(
            { AllTabTasks() },
            { ActiveTabTasks() },
            { CompletedTabTasks() }
        ),
    )
    AddTaskButton {

    }


}