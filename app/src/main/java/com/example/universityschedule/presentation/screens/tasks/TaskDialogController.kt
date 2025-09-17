package com.example.universityschedule.presentation.screens.tasks

import androidx.compose.runtime.MutableState
import com.example.universityschedule.presentation.common.DialogEvent

enum class Priority {
    LOW, MEDIUM, HIGH
}

enum class LessonChip {
    NONE, CALCULUS, PHYSICS, CHEMISTRY, MATH, PROGRAMMING, SPORT
}

interface TaskDialogController {
    val dialogTitle: MutableState<String>
    val dialogDescription: MutableState<String>
    val dialogDueDate: MutableState<String>
    val dialogPriority: MutableState<Priority>
    val dialogRelatedLesson: MutableState<LessonChip>
    fun onDialogEvent(event: DialogEvent)
}