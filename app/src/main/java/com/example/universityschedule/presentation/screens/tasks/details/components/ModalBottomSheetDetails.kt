package com.example.universityschedule.presentation.screens.tasks.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.DetailsButton
import com.example.universityschedule.presentation.common.components.PriorityChipsRow
import com.example.universityschedule.presentation.common.components.UniversalDropdown
import com.example.universityschedule.presentation.common.components.UniversalTextField
import com.example.universityschedule.presentation.screens.tasks.components.Priority
import com.example.universityschedule.presentation.util.dimens

@Preview
@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetDetails() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isSheetOpen by remember { mutableStateOf(false) }
    val titleState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    val priorityState = remember { mutableStateOf(Priority.Medium) }
    val lessons = listOf(
        "math",
        "programming",
        "algorithms"
    )
    var selected by remember { mutableStateOf("Literature") }

    var dialogPriority = remember { mutableStateOf(Priority.Medium) }

    LaunchedEffect(Unit) {
        try {
            sheetState.show() // suspend - работает внутри LaunchedEffect
        } catch (_: Exception) { /* preview может кидать, игнорируем */ }
    }

    ModalBottomSheet(onDismissRequest = { isSheetOpen = false }, sheetState = sheetState) {
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
                value = titleState.value,
                onValueChange = { titleState.value = it },
                label = "Title",
                placeholder = "Enter task title",
                singleLine = true,
            )
            UniversalTextField(
                value =
                    descriptionState.value,
                onValueChange = {
                    descriptionState.value = it
                },
                label = "Description",
                placeholder = "Enter task description",
                singleLine = false,
                maxLines = 3
            )


            Text(
                "Priority",
                style = MaterialTheme.typography.bodyMedium
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
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

            UniversalDropdown(
                items = lessons,
                selectedItem = selected,
                onItemSelected = { selected = it },
                label = "Select Lesson"
            )

            Spacer(Modifier.height(MaterialTheme.dimens.heightMedium))

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
                    onClick = { TODO() },
                    textColor = colorResource(R.color.selectedBottom)
                )
                DetailsButton(
                    modifier = Modifier.weight(1f),
                    text = "Save Changes",
                    color = colorResource(R.color.selectedBottom),
                    icon = null,
                    sizeIcon = null,
                    onClick = { TODO() }
                )
            }


        }
    }
}