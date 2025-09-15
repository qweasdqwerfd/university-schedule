package com.example.universityschedule.presentation.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.universityschedule.presentation.Dimens.MediumPadding1
import com.example.universityschedule.presentation.Dimens.MediumPadding2
import com.example.universityschedule.presentation.Dimens.MediumPadding3
import com.example.universityschedule.presentation.screens.calendar.components.LessonCard
import com.example.universityschedule.presentation.screens.calendar.components.TitleDate
import com.example.universityschedule.presentation.screens.calendar.components.WeekCalendar
import com.example.universityschedule.presentation.screens.lessons.Lesson
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.time.LocalDate

@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(navController: NavController) {

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val pagerState = rememberPagerState(initialPage = 1000) // старт где-то в центре

    // Демонстрационные пары
    val lessons = listOf(
        Lesson("Calculus", "09:00 - 10:30", "Math Building, Room 101", "Dr. Smith"),
        Lesson("Physics", "11:00 - 12:30", "Science Center, Room 205", "Dr. Johnson")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MediumPadding1)
    ) {

        HorizontalPager(
            count = Int.MAX_VALUE, // "бесконечный" календарь
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            // Начало недели (понедельник) + сдвиг по свайпу
            val startOfWeek = LocalDate.now()
                .with(java.time.DayOfWeek.MONDAY)
                .plusWeeks((page - 1000).toLong())

            val daysOfWeek = (0..6).map { startOfWeek.plusDays(it.toLong()) }

            WeekCalendar(
                days = daysOfWeek,
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )
        }

        Spacer(Modifier.height(MediumPadding2))

        TitleDate(selectedDate)

        Spacer(Modifier.height(MediumPadding2))

        Text(
            text = "Пары",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(MediumPadding2))

        LazyColumn {
            items(lessons.size) { index ->
                LessonCard(lessons[index])
                Spacer(Modifier.height(MediumPadding3))
            }
        }

    }
}