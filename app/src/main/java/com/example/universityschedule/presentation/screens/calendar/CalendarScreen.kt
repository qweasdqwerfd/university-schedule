@file:OptIn(ExperimentalPagerApi::class)

package com.example.universityschedule.presentation.screens.calendar

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.LessonCard
import com.example.universityschedule.presentation.common.LessonType
import com.example.universityschedule.presentation.common.components.MainDivider
import com.example.universityschedule.presentation.screens.calendar.components.TitleDate
import com.example.universityschedule.presentation.screens.calendar.components.WeekHeader
import com.example.universityschedule.presentation.screens.lessons.Lesson
import com.example.universityschedule.presentation.util.dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.kizitonwose.calendar.compose.WeekCalendar
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Locale
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarScreen(
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    titleFont: FontWeight = FontWeight.ExtraBold
) {
    val coroutineScope = rememberCoroutineScope()

    val today = remember { LocalDate.now() }
    // если сегодня - воскресенье, базовая дата = понедельник (удаляем вс из UI полностью)
    val baseDate = remember {
        if (today.dayOfWeek == DayOfWeek.SUNDAY) today.plusDays(1) else today
    }

    val initialPage = 1000
    val pagerState = rememberPagerState(initialPage = initialPage)

    // --- вспомогательные функции, работающие с "шагами" ---
    fun pageToDate(page: Int): LocalDate {
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
    // -------------------------------------------------------------------------------

    // Текущая дата, синхронизированная с pagerState, но без воскресений
    val currentDate by remember {
        derivedStateOf {
            pageToDate(pagerState.currentPage)
        }
    }

    // Вычисляем неделю (Mon..Sat) для текущей даты
    val startOfWeek = remember(currentDate) { currentDate.with(DayOfWeek.MONDAY) }
    val daysOfWeek = remember(startOfWeek) { (0..5).map { startOfWeek.plusDays(it.toLong()) } }

    Column(modifier = Modifier.fillMaxSize()) {

        WeekHeader(
            days = daysOfWeek,
            selectedDate = currentDate,
            onDateSelected = { clickedDate ->
                val diff = nonSundayStepsBetween(currentDate, clickedDate)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + diff)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(MaterialTheme.dimens.space12))

        TitleDate(selectedDate = currentDate)

        Spacer(Modifier.height(MaterialTheme.dimens.space12))

        HorizontalPager(
            count = Int.MAX_VALUE,
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            // Для каждой страницы показываем расписание соответствующего дня — используем pageToDate
            val pageDate = remember(page) { pageToDate(page) }

            ScheduleContent(
                date = pageDate,
                titleStyle = titleStyle,
                titleFont = titleFont
            )
        }
    }
}


@Composable
fun ScheduleContent(
    date: LocalDate,
    titleStyle: TextStyle,
    titleFont: FontWeight
) {
    // демонстрационные пары

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
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

        item {
            LessonCard(
                title = "Calculus",
                time = "11:00 - 12:30",
                location = "Math Building, Room 101",
                teacher = "Dr. Smith",
                type = LessonType.LECTURE,
            )
            Spacer(Modifier.height(MaterialTheme.dimens.space8))

            LessonCard(
                title = "Physics",
                time = "12:35 - 14:05",
                location = "Science Center, Room 205",
                teacher = "Dr. Johnson",
                type = LessonType.LAB,
            )
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

