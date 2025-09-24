package com.example.universityschedule.presentation.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.screens.lessons.components.ChipsLessons
import com.example.universityschedule.presentation.screens.tasks.components.ChipsLvL
import com.example.universityschedule.presentation.common.components.UniversalTextField
import com.example.universityschedule.presentation.screens.tasks.components.LessonChip
import com.example.universityschedule.presentation.screens.tasks.components.Priority
import com.example.universityschedule.presentation.util.dimens

@Composable
fun NewTaskScreen(
    viewModel: TaskViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.dimens.space16,
                top = MaterialTheme.dimens.space14,
                end = MaterialTheme.dimens.space16
            )

    ) {
        Text(
            "Title",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        UniversalTextField(
            value = viewModel.dialogTitle.value,
            onValueChange = { viewModel.dialogTitle.value = it },
            label = "Enter task title",
            placeholder = "Enter task title",
            singleLine = true
        )

        Text(
            "Description",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface

        )


        UniversalTextField(
            value = viewModel.dialogDescription.value,
            onValueChange = { viewModel.dialogDescription.value = it },
            label = "Enter task description",
            placeholder = "Enter task description",
            singleLine = false,
            maxLines = 5,
            minHeight = 150.dp,
            maxHeight = 300.dp
        )

        Text(
            "Due Date",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )


        UniversalTextField(
            value = viewModel.dialogDueDate.value,
            onValueChange = { viewModel.dialogDueDate.value = it },
            label = "Mon, Mar 31, 09:42 PM",
            placeholder = "Enter task due date",
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "calendar",
                    Modifier.size(23.dp)
                )
            }
        )

        Text(
            "Priority",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )


        ChipsLvL(
            selectedPriority = viewModel.dialogPriority.value.name,
            onPrioritySelected = { priorityName ->
                val priority = Priority.valueOf(priorityName)
                viewModel.dialogPriority.value = priority
            }
        )

        Text(
            "Related Lesson (Optional)",
            modifier = Modifier.padding(top = MaterialTheme.dimens.space10),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        ChipsLessons(
            selectedLessons = viewModel.dialogRelatedLesson.value.name,
            selectLessons = { lessonName ->
                val lesson = LessonChip.valueOf(lessonName)
                viewModel.dialogRelatedLesson.value = lesson
            }
        )
    }
}