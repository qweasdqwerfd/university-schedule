package com.example.universityschedule.presentation.screens.lessons

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Displayable
import java.time.LocalDate

enum class LessonColor(val color: Color) {
    BLUE(Color(0xFF3F51B5)),
    ORANGE(Color(0xFFFFA726)),
    GREEN(Color(0xFF66BB6A)),
    CYAN(Color(0xFF26C6DA)),
    YELLOW(Color(0xFFFFEE58)),
    RED(Color(0xFFE57373));
}

enum class AllColors(val color: Color) {
    RED(Color(0xFFca2244)),
    ORANGE(Color(0xFFFFA726)),
    GREEN(Color(0xFF31b947)),
    BLUE(Color(0xFF3F51B5)),
    Purple(Color(0xFF9C27B0)),
    CYAN(Color(0xFF26C6DA)),
    YELLOW(Color(0xFFFFEE58)),
    WhITE_BLUE(Color(0xFFE57373))
}

enum class DayOfWeek(override val displayName: String) : Displayable {
    Sun("Вс"),
    Mon("Пн"),
    Tue("Вт"),
    Wed("Ср"),
    Thu("Чт"),
    Fri("Пт"),
    Sat("Сб")
}


interface LessonDialogController {
    val dialogTitle: MutableState<String>
    val dialogProfessor: MutableState<String>
    val dialogLocation: MutableState<String>
    val dialogStartTime: MutableState<String>
    val dialogEndTime: MutableState<String>
    val dialogDayOfWeek: MutableState<LocalDate?>
    val dialogColor: MutableState<String>

    fun onDialogEvent(event: DialogEvent)
}