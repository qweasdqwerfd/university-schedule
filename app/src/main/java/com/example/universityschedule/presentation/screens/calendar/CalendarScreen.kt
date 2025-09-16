@file:OptIn(ExperimentalPagerApi::class)

package com.example.universityschedule.presentation.screens.calendar

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.Dimens.MediumPadding
import com.example.universityschedule.presentation.Dimens.MediumPadding1
import com.example.universityschedule.presentation.Dimens.MediumPadding2
import com.example.universityschedule.presentation.Dimens.MediumPadding3
import com.example.universityschedule.presentation.common.components.MainDivider
import com.example.universityschedule.presentation.screens.calendar.components.LessonCard
import com.example.universityschedule.presentation.screens.calendar.components.TitleDate
import com.example.universityschedule.presentation.screens.calendar.components.WeekCalendar
import com.example.universityschedule.presentation.screens.lessons.Lesson
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kizitonwose.calendar.compose.WeekCalendar
import java.time.DayOfWeek
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarScreen(
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    titleFont: FontWeight = FontWeight.ExtraBold
) {

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
//            .padding(horizontal = MediumPadding1)
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
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(bottom = 150.dp)
        ) {
            item {
                Spacer(Modifier.height(MediumPadding2))

                MainDivider()

                TitleDate(selectedDate)

                MainDivider()

                Spacer(Modifier.height(MediumPadding2))

                Text(
                    text = "Пары",
                    modifier = Modifier
                        .padding(horizontal = MediumPadding1),
                    style = titleStyle,
                    fontWeight = titleFont
                )

                Spacer(Modifier.height(MediumPadding2))
            }



            items(lessons.size) { index ->
                LessonCard(lessons[index])
                Spacer(Modifier.height(MediumPadding3))
            }

            item {
                Spacer(Modifier.height(MediumPadding))

                Text(
                    text = "Задания, которые нужно выполнить сегодня",
                    modifier = Modifier
                        .padding(horizontal = MediumPadding1),
                    style = titleStyle,
                    fontWeight = titleFont
                )

                Spacer(Modifier.height(MediumPadding1))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit1),
                        contentDescription = "edit",
                        modifier = Modifier
                            .size(80.dp),
                        tint = Color(0xFF3F51B5),

                        )
                    Spacer(Modifier.height(MediumPadding1))
                    Text(
                        text = "Нет задач на этот день",
                        style = titleStyle
                    )

                }
            }
        }


    }
}