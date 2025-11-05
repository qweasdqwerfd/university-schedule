package com.example.universityschedule.presentation.screens.calendar.algsOfSun

import java.time.DayOfWeek
import java.time.LocalDate

fun nonSundayStepsBetween(from: LocalDate, to: LocalDate): Int {
    if (from == to) return 0
    var sign = if (to.isAfter(from)) 1 else -1
    var steps = 0
    var d = from
    while (d != to) {
        d = if (sign > 0) d.plusDays(1) else d.minusDays(1)
        if (d.dayOfWeek != DayOfWeek.SUNDAY) steps += sign
    }
    return steps
}