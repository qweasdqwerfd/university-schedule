package com.example.universityschedule.presentation.screens.tasks.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
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
        }
    }
}