package com.example.universityschedule.data.view_models.contracts.controllers

import androidx.compose.runtime.MutableState
import com.example.universityschedule.data.entities.TaskItem
import com.example.universityschedule.data.view_models.contracts.events.DialogEvent

enum class Priority { LOW, MEDIUM, HIGH }
enum class Lesson { NONE, CALCULUS, PHYSICS, CHEMISTRY, Math, Programming, Sport }

interface TaskDialogController {
    val dialogTitle: MutableState<String>
    val dialogDescription: MutableState<String>
    val dialogDueDate: MutableState<String>
    val dialogPriority: MutableState<Priority>
    val dialogRelatedLesson: MutableState<Lesson>
    val openDialog: MutableState<Boolean>
    fun onDialogEvent(event: DialogEvent)
}