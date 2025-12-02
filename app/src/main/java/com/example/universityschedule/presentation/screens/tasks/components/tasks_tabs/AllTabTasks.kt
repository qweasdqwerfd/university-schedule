package com.example.universityschedule.presentation.screens.tasks.components.tasks_tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.universityschedule.domain.model.TaskItemEntity
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.components.CardTaskPanel

@Composable
fun AllTabTasks(tasks: List<TaskItemEntity>, viewModel: TaskViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(tasks) {
            CardTaskPanel(
                item = it,
                onEvent = { event -> viewModel.onDialogEvent(event) },
            )
        }
    }
}