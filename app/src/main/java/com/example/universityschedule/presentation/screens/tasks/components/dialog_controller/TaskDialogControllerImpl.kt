package com.example.universityschedule.presentation.screens.tasks.components.dialog_controller

import androidx.compose.runtime.mutableStateOf
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.screens.tasks.details.DetailsEvent
import java.time.LocalDateTime
import javax.inject.Inject

class TaskDialogControllerImpl @Inject constructor() : TaskDialogController {
    override val dialogTitle = mutableStateOf("")
    override val dialogDescription = mutableStateOf("")
    override val dialogDueDate = mutableStateOf<LocalDateTime?>(null)
    override val dialogPriority = mutableStateOf(Priority.Medium)
    override val dialogRelatedLesson = mutableStateOf(LessonChip.NONE)

    override fun onDialogEvent(event: DialogEvent) {}

    override fun onBottomDialogEvent(event: DetailsEvent) {}
}