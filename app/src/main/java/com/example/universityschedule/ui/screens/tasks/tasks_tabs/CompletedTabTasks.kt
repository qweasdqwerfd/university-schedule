package com.example.universityschedule.ui.screens.tasks.tasks_tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.universityschedule.data.models.TaskModel

@Composable
fun CompletedTabTasks(tasks: List<TaskModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),

        ) {
        Text("completed")

    }

}