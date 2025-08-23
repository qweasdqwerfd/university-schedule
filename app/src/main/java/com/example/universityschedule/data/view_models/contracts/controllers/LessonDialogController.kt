package com.example.universityschedule.data.view_models.contracts.controllers

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.universityschedule.data.view_models.contracts.events.DialogEvent

enum class DayOfWeek { Mon, Tue, Wed, Thu, Fri, Sat, Sun }
enum class LessonColor(val color: Color) {
    BLUE(Color(0xFF3F51B5)),
    ORANGE(Color(0xFFFFA726)),
    GREEN(Color(0xFF66BB6A)),
    CYAN(Color(0xFF26C6DA)),
    YELLOW(Color(0xFFFFEE58)),
    RED(Color(0xFFE57373));
}


interface LessonDialogController {
    val dialogTitle: MutableState<String>
    val dialogProfessor: MutableState<String>
    val dialogLocation: MutableState<String>
    val dialogStartTime: MutableState<String>
    val dialogEndTime: MutableState<String>
    val dialogDayOfWeek: MutableState<String>
    val dialogColor: MutableState<String>

    fun onDialogEvent(event: DialogEvent)
}