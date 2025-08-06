package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.universityschedule.ui.castom_components.Chips.ChipsLessons
import com.example.universityschedule.ui.castom_components.Chips.ChipsLvL
import com.example.universityschedule.ui.castom_components.IconButton.SaveIconButton
import com.example.universityschedule.ui.castom_components.TextField.DefaultTextField
import com.example.universityschedule.ui.castom_components.TextField.DescriptionTextField
import com.example.universityschedule.ui.castom_components.TextField.IconTextField
import com.example.universityschedule.data.TaskViewModel
import com.example.universityschedule.data.models.TaskModel

@Composable
fun AddNewTask(
    onSave: (TaskModel) -> Unit,
    onCancel: () -> Unit,
    taskViewModel: TaskViewModel = viewModel()
) {
    var selectedPriority by remember { mutableStateOf("Medium") }
    var selectedLessons by remember { mutableStateOf("None") }
    var titleText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }


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
            value = titleText,
            onValueChange = { titleText = it }
        )

        Text(
            "Description",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface

        )
        DescriptionTextField(
            value = descriptionText,
            onValueChange = { descriptionText = it }
        )

        Text(
            "Due Date",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        IconTextField(
            value = dueDate,
            onValueChanged = { dueDate = it }
        )

        Text(
            "Priority",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )


        ChipsLvL(
            selectedPriority = selectedPriority,
            onPrioritySelected = { priority ->
                selectedPriority = priority
            }
        )

        Text(
            "Related Lesson (Optional)",
            modifier = Modifier.padding(top = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        ChipsLessons(
            selectedLessons = selectedLessons,
            selectLessons = { lesson ->
                selectedLessons = lesson
            }
        )

        SaveIconButton {
            onSave(
                TaskModel(
                    title = titleText,
                    description = descriptionText,
                    dueDate = dueDate,
                    priority = selectedPriority,
                    lesson = selectedLessons
                )
            )
        }
    }

}