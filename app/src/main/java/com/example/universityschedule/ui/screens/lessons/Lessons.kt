package com.example.universityschedule.ui.screens.lessons

import androidx.compose.runtime.Composable
import com.example.universityschedule.ui.castom_components.IconButton.AddTaskButton
import com.example.universityschedule.ui.screens.ScrollableTabRowPager
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.AllTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.FridayTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.MondayTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.SaturdayTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.SundayTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.ThursdayTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.TuesdayTabLessons
import com.example.universityschedule.ui.screens.lessons.lessons_tabs.WednesdayTabLessons

@Composable
fun Lessons() {

    ScrollableTabRowPager(
        tabs = listOf("All", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
        pages = listOf(
            { AllTabLessons() },
            { MondayTabLessons() },
            { TuesdayTabLessons() },
            { WednesdayTabLessons() },
            { ThursdayTabLessons() },
            { FridayTabLessons() },
            { SaturdayTabLessons() },
            { SundayTabLessons() },
        ),
    )

    AddTaskButton {

    }
}