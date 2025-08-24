package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.custom_components.Chips.ChipsLessons
import com.example.universityschedule.ui.custom_components.Chips.ChipsLvL
import com.example.universityschedule.ui.custom_components.TextField.DefaultTextField
import com.example.universityschedule.ui.custom_components.TextField.DescriptionTextField
import com.example.universityschedule.ui.custom_components.TextField.IconTextField

@Composable
fun NewTaskScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 17.dp, top = 15.dp, end = 17.dp)

    ) {
        Text(
            "Title",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        DefaultTextField(
            value = viewModel.dialogTitle.value,
            onValueChange = { viewModel.dialogTitle.value = it }
        )

        Text(
            "Description",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface

        )
        DescriptionTextField(
            value = viewModel.dialogDescription.value,
            onValueChange = { viewModel.dialogDescription.value = it }
        )

        Text(
            "Due Date",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        IconTextField(
            value = viewModel.dialogDueDate.value,
            onValueChanged = { viewModel.dialogDueDate.value = it }
        )

        Text(
            "Priority",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )


        ChipsLvL(
            selectedPriority = viewModel.dialogPriority.value.name,
            onPrioritySelected = { viewModel.dialogPriority.value  }
        )

        Text(
            "Related Lesson (Optional)",
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        ChipsLessons(
            selectedLessons = viewModel.dialogRelatedLesson.value.name,
            selectLessons = { viewModel.dialogRelatedLesson.value }
        )
    }
}