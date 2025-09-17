@file:OptIn(ExperimentalPagerApi::class)

package com.example.universityschedule.presentation.screens.calendar

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.MainDivider
import com.example.universityschedule.presentation.screens.calendar.components.LessonCard
import com.example.universityschedule.presentation.screens.calendar.components.TitleDate
import com.example.universityschedule.presentation.screens.calendar.components.WeekCalendar
import com.example.universityschedule.presentation.screens.lessons.Lesson
import com.example.universityschedule.presentation.util.dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kizitonwose.calendar.compose.WeekCalendar
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarScreen(
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    titleFont: FontWeight = FontWeight.ExtraBold
) {
    val coroutineScope = rememberCoroutineScope()

    val today = remember { LocalDate.now() }
    val initialPage = 1000
    val pagerState = rememberPagerState(initialPage = initialPage)

    HorizontalPager(
        count = Int.MAX_VALUE,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->

        // каждая страница = отдельный день
        val currentDate = remember(page) { today.plusDays((page - initialPage).toLong()) }

        // вычисляем неделю для текущей даты
        val startOfWeek = currentDate.with(DayOfWeek.MONDAY)
        val daysOfWeek = (0..6).map { startOfWeek.plusDays(it.toLong()) }

        Column(modifier = Modifier.fillMaxSize()) {

            // панель недели синхронизирована с pager
            WeekCalendar(
                days = daysOfWeek,
                selectedDate = currentDate,
                onDateSelected = { clickedDate ->
                    val diff = ChronoUnit.DAYS.between(currentDate, clickedDate).toInt()
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page + diff)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(MaterialTheme.dimens.space12))

            TitleDate(selectedDate = currentDate)

            Spacer(Modifier.height(MaterialTheme.dimens.space12))

            // демонстрационные пары
            val lessons = listOf(
                Lesson("Calculus", "09:00 - 10:30", "Math Building, Room 101", "Dr. Smith"),
                Lesson("Physics", "11:00 - 12:30", "Science Center, Room 205", "Dr. Johnson")
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 150.dp)
            ) {
                item {
                    Text(
                        text = "Пары",
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space20),
                        style = titleStyle,
                        fontWeight = titleFont
                    )
                    Spacer(Modifier.height(MaterialTheme.dimens.space12))
                }

                items(lessons.size) { index ->
                    LessonCard(lessons[index])
                    Spacer(Modifier.height(MaterialTheme.dimens.space8))
                }

                item {
                    Spacer(Modifier.height(MaterialTheme.dimens.space36))
                    Text(
                        text = "Задания, которые нужно выполнить сегодня",
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space20),
                        style = titleStyle,
                        fontWeight = titleFont
                    )
                    Spacer(Modifier.height(MaterialTheme.dimens.space20))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit1),
                            contentDescription = "edit",
                            modifier = Modifier.size(80.dp),
                            tint = Color(0xFF3F51B5),
                        )
                        Spacer(Modifier.height(MaterialTheme.dimens.space20))
                        Text(text = "Нет задач на этот день", style = titleStyle)
                    }
                    Spacer(Modifier.height(48.dp))
                }
            }
        }
    }
}