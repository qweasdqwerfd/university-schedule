package com.example.universityschedule.presentation.screens.tasks.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.DetailsButton
import com.example.universityschedule.presentation.common.components.PriorityChipsRow
import com.example.universityschedule.presentation.common.components.UniversalDropdown
import com.example.universityschedule.presentation.common.components.UniversalTextField
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.LessonChip
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Priority
import com.example.universityschedule.presentation.screens.tasks.details.DetailsEvent
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsViewModel
import com.example.universityschedule.presentation.util.dimens

@SuppressLint("RememberReturnType", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetDetails(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    title: MutableState<String>,
    description: MutableState<String>,
    selectedLesson: MutableState<LessonChip>,
    dueDate: MutableState<String>,
    dialogPriority: MutableState<Priority>,
    check: MutableState<Boolean>,
    lessonList: MutableState<List<LessonChip>>,
    viewModel: TaskDetailsViewModel
) {

    ModalBottomSheet(onDismissRequest = { onDismiss }, sheetState = sheetState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = MaterialTheme.dimens.space20,
                    vertical = MaterialTheme.dimens.space20
                )
        ) {

            Spacer(Modifier.height(MaterialTheme.dimens.heightMedium))

            Text(
                "Edit Task",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(MaterialTheme.dimens.heightSmallPlus))

            UniversalTextField(
                value = title.value,
                onValueChange = { title.value = it },
                label = "Title",
                placeholder = "Enter task title",
                singleLine = true,
            )
            UniversalTextField(
                value = description.value,
                onValueChange = {
                    description.value = it
                },
                label = "Description",
                placeholder = "Enter task description",
                singleLine = false,
                maxLines = 3
            )
            UniversalTextField(
                value = dueDate.value,
                onValueChange = { dueDate.value = it },
                label = "Due Date",
                placeholder = "Enter due date",
                singleLine = true
            )

            Text(
                "Priority",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

            PriorityChipsRow(
                selectedPriority = dialogPriority.value,
                onPrioritySelected = { dialogPriority.value = it },
            ) { priority, isSelected, onClick ->
                PriorityChipCompact(priority, isSelected, onClick)
            }

            Spacer(Modifier.height(MaterialTheme.dimens.heightSmallPlus))

            Text(
                "Related Lesson",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

            UniversalDropdown(
                items = lessonList.value,
                selectedItem = selectedLesson.value,
                onItemSelected = { lesson ->
                    selectedLesson.value = lesson
                },
                label = "Select Lesson"
            )

            Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmallPlus))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.offset(x = (-12).dp)) {
                    Checkbox(
                        checked = check.value,
                        onCheckedChange = { check.value = it }
                    )
                }

                Text(
                    "Done",
                    modifier = Modifier.offset(x = (-10).dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )



            }
            Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmallPlus))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.space6),
            ) {
                DetailsButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    color = Color.White,
                    icon = null,
                    sizeIcon = null,
                    onClick = { viewModel.onBottomDialogEvent(DetailsEvent.OnCancel) },
                    textColor = colorResource(R.color.selectedBottom)
                )
                DetailsButton(
                    modifier = Modifier.weight(1f),
                    text = "Save Changes",
                    color = colorResource(R.color.selectedBottom),
                    icon = null,
                    sizeIcon = null,
                    onClick = { viewModel.onBottomDialogEvent(DetailsEvent.OnConfirm) }
                )
            }


        }
    }
}