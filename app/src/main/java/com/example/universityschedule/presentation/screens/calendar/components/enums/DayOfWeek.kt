package com.example.universityschedule.presentation.screens.calendar.components.enums

import com.example.universityschedule.presentation.common.dialog_controller.Displayable

enum class DayOfWeek(override val displayName: String) : Displayable {
    Sun("Вс"),
    Mon("Пн"),
    Tue("Вт"),
    Wed("Ср"),
    Thu("Чт"),
    Fri("Пт"),
    Sat("Сб")
}