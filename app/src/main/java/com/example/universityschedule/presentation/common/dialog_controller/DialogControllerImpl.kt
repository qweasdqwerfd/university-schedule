package com.example.universityschedule.presentation.common.dialog_controller

import androidx.compose.runtime.mutableStateOf
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.screens.tasks.details.CRUDEvent
import java.time.LocalDateTime
import javax.inject.Inject

class DialogControllerImpl @Inject constructor() : DialogController {
    override val dialogTitle = mutableStateOf("")
    override val dialogDescription = mutableStateOf("")
    override val dialogDueDate = mutableStateOf<LocalDateTime?>(null)
    override val dialogPriority = mutableStateOf(Priority.Medium)
    override val dialogRelatedLesson = mutableStateOf(LessonChip.NONE)

    override fun onDialogEvent(event: DialogEvent) {}

    override fun onCRUDEvent(event: CRUDEvent) {}
}