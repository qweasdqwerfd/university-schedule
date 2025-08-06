package com.example.universityschedule.ui.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.universityschedule.ui.castom_components.IconButton.AddTaskButton

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(navController: NavController) {

    val days = remember { generateDays() }
    var selectedDay by remember { mutableStateOf(days.first()) }

    Column {
        ScrollableTabRow(
            selectedTabIndex = days.indexOfFirst { it.id == selectedDay.id },
            edgePadding = 0.dp

        ) {
            days.forEach { day ->
                Tab(
                    selected = day.id == selectedDay.id,
                    onClick = {
                        selectedDay = day
                        navController.navigate("day/${day.id}")
                    },
                    text = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(day.dayName)
                            Text("${day.date}")
                            Text(day.month)
                        }
                    }
                )
            }
        }

        // Контент текущего дня
        DayContent(day = selectedDay)
        AddTaskButton {
            navController.navigate("addTask")
        }
    }

}