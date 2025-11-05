package com.example.universityschedule.presentation.screens.calendar.algsOfSun

import java.time.DayOfWeek
import java.time.LocalDate

fun pageToDate(
    initialPage: Int,
    page: Int,
    baseDate: LocalDate
): LocalDate {

    val delta = page - initialPage
    var d = baseDate

    if (delta > 0) {
        var steps = delta
        while (steps > 0) {
            d = d.plusDays(1)
            if (d.dayOfWeek != DayOfWeek.SUNDAY) steps--
        }
    } else if (delta < 0) {
        var steps = -delta
        while (steps > 0) {
            d = d.minusDays(1)
            if (d.dayOfWeek != DayOfWeek.SUNDAY) steps--
        }
    }

    return d

}