package com.example.universityschedule.presentation.screens.tasks.components.dialog_controller

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.screens.tasks.details.DetailsEvent

interface Displayable { val displayName: String }

enum class Priority(override val displayName: String) : Displayable {
    Low("Низкий"),
    Medium("Средний"),
    High("Высокий")
}
enum class PriorityColor(val color: Color) {
    HIGH(Color(0xFFca2244)),
    MEDIUM(Color(0xFFf6c610)),
    LOW(Color(0xFF31b947))
}

enum class LessonChip(override val displayName: String) : Displayable {
    NONE("Пусто"),
    CALCULUS("Исчисление"),
    PHYSICS("Физика"),
    CHEMISTRY("Химия"),
    MATH("Математика"),
    PROGRAMMING("Программирование"),
    SPORT("Спорт")
}

interface TaskDialogController {
    val dialogTitle: MutableState<String>
    val dialogDescription: MutableState<String>
    val dialogDueDate: MutableState<String>
    val dialogPriority: MutableState<Priority>
    val dialogRelatedLesson: MutableState<LessonChip>
    fun onDialogEvent(event: DialogEvent)
    fun onBottomDialogEvent(event: DetailsEvent)
}