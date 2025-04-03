package com.example.universityschedule.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayScreen(dayId: String) {
    val day = remember(dayId) {
        generateDays().firstOrNull { it.id == dayId } ?: generateDays().first()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${day.dayName}, ${day.date} ${day.month}",
//            style = MaterialTheme.typography.h4
        )

        // Здесь может быть ваш контент для дня:
        // - Список событий
        // - Расписание
        // - Статистика и т.д.
    }
}