package com.example.universityschedule.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.universityschedule.data.view_models.TaskViewModel
import com.example.universityschedule.ui.custom_components.Chips.ChipsLessons
import com.example.universityschedule.ui.custom_components.Chips.ChipsLvL
import com.example.universityschedule.ui.custom_components.TextField.DefaultTextField
import com.example.universityschedule.ui.custom_components.TextField.DescriptionTextField
import com.example.universityschedule.ui.custom_components.TextField.IconTextField

@Composable
fun NewTaskScreen(
    navHostController: NavHostController
) {





//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(start = 17.dp, top = 15.dp, end = 17.dp)
//
//    ) {
//        Text(
//            "Title",
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//        DefaultTextField(
//            value = viewModel.titleText,
//            onValueChange = viewModel::onTitleChange
//        )
//
//        Text(
//            "Description",
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.onSurface
//
//        )
//        DescriptionTextField(
//            value = viewModel.descriptionText,
//            onValueChange = viewModel::onDescriptionChange
//        )
//
//        Text(
//            "Due Date",
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//        IconTextField(
//            value = viewModel.dueDate,
//            onValueChanged = viewModel::onDueDateChange
//        )
//
//        Text(
//            "Priority",
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//
//
//        ChipsLvL(
//            selectedPriority = viewModel.selectedPriority,
//            onPrioritySelected = viewModel::onPriorityChange
//        )
//
//        Text(
//            "Related Lesson (Optional)",
//            modifier = Modifier.padding(top = 10.dp),
//            style = MaterialTheme.typography.titleMedium,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//
//        ChipsLessons(
//            selectedLessons = viewModel.selectedLessons,
//            selectLessons = viewModel::onLessonChange
//        )
//
//
//
//    }

}