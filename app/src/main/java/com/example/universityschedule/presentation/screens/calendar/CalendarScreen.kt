@file:OptIn(ExperimentalPagerApi::class)

package com.example.universityschedule.presentation.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.universityschedule.presentation.screens.calendar.components.TitleDate
import com.example.universityschedule.presentation.screens.calendar.components.WeekHeader
import com.example.universityschedule.presentation.util.dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

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