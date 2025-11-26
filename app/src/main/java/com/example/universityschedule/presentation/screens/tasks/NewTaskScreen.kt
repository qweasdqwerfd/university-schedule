package com.example.universityschedule.presentation.screens.tasks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.EnumChipsRow
import com.example.universityschedule.presentation.common.components.UniversalTextField
import com.example.universityschedule.presentation.screens.calendar.components.enums.LessonColor
import com.example.universityschedule.presentation.screens.tasks.components.UniversalEnumChip
import com.example.universityschedule.presentation.common.dialog_controller.LessonChip
import com.example.universityschedule.presentation.common.dialog_controller.Priority
import com.example.universityschedule.presentation.common.dialog_controller.PriorityColor
import com.example.universityschedule.presentation.util.dimens

@RequiresApi(Build.VERSION_CODES.O)
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
            "Заголовок",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        UniversalTextField(
            value = viewModel.dialogTitle.value,
            onValueChange = { viewModel.dialogTitle.value = it },
            label = "Введите название задачи",
            placeholder = "Введите название задачи",
            maxChars = 30
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        Text(
            "Описание",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface

        )


        UniversalTextField(
            value = viewModel.dialogDescription.value,
            onValueChange = { viewModel.dialogDescription.value = it },
            label = "Введите описание задачи",
            placeholder = "Введите описание задачи",
            singleLine = false,
            maxLines = 5,
            minHeight = 150.dp,
            maxHeight = 300.dp,
            maxChars = 100
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))


        Text(
            "Установленный срок",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        DateTimePickerField(
            value = viewModel.dialogDueDate.value,
            onValueChange = { viewModel.dialogDueDate.value = it },
            label = "Пон, Март 31, 09:42",
            placeholder = ""
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))


        Text(
            "Приоритет",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))


        EnumChipsRow(
            selectedItem = viewModel.dialogPriority.value,
            onItemSelected = { viewModel.dialogPriority.value = it },
        ) { item, isSelected, onClick ->
            UniversalEnumChip(
                item = item,
                isSelected = isSelected,
                onClick = onClick,
                colorProvider = { enumItem ->
                    when (enumItem) {
                        Priority.Low -> PriorityColor.LOW.color
                        Priority.High -> PriorityColor.HIGH.color
                        else -> PriorityColor.MEDIUM.color
                    }
                },

            )
        }

        Spacer(Modifier.height(MaterialTheme.dimens.heightSmallMinus))

        Text(
            "Связанная дисциплина",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))


        EnumChipsRow(
            selectedItem = viewModel.dialogRelatedLesson.value,
            onItemSelected = { viewModel.dialogRelatedLesson.value = it }
        ) { item, isSelected, onClick ->
            UniversalEnumChip(
                item = item,
                isSelected = isSelected,
                onClick = onClick,
                icon = R.drawable.lessons,
                colorProvider = { enumItem ->
                    when (enumItem) {
                        LessonChip.MATH -> LessonColor.ORANGE.color
                        LessonChip.CALCULUS -> LessonColor.RED.color
                        LessonChip.NONE -> LessonColor.BLUE.color
                        LessonChip.SPORT -> LessonColor.CYAN.color
                        LessonChip.PHYSICS -> LessonColor.GREEN.color
                        LessonChip.CHEMISTRY -> LessonColor.YELLOW.color
                        else -> Color.Gray
                    }
                }
            )
        }
    }
}