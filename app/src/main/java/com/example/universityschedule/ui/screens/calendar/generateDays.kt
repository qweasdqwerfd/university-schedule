package com.example.universityschedule.ui.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.universityschedule.data.models.DayModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun generateDays(): List<DayModel> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return (0 until 14).map { offset ->
        val date = LocalDate.now().plusDays(offset.toLong())
        DayModel(
            id = date.format(formatter),
            dayName = date.format(DateTimeFormatter.ofPattern("E", Locale.ENGLISH)),
            date = date.dayOfMonth,
            month = date.format(DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH)),
            isSelected = offset == 0
        )
    }
}