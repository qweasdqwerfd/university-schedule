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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.presentation.screens.calendar.algsOfSun.nonSundayStepsBetween
import com.example.universityschedule.presentation.screens.calendar.algsOfSun.pageToDate
import com.example.universityschedule.presentation.screens.calendar.components.TitleDate
import com.example.universityschedule.presentation.screens.calendar.header.WeekHeader
import com.example.universityschedule.presentation.screens.search.SearchViewModel
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.util.dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarScreen(
    calendarViewModel: CalendarViewModel,
    searchViewModel: SearchViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    titleFont: FontWeight = FontWeight.ExtraBold,
) {
    val coroutineScope = rememberCoroutineScope()
    val selectedDate by calendarViewModel.selectedDate.collectAsState()
    val today = remember { LocalDate.now() }
    val baseDate = remember { if (today.dayOfWeek == DayOfWeek.SUNDAY) today.plusDays(1) else today }
    val initialPage = 1000
    val pagerState = rememberPagerState(initialPage = initialPage)

    val currentDate by remember {
        derivedStateOf { pageToDate(initialPage, pagerState.currentPage, baseDate) }
    }

    // throttle обновления selectedDate
    LaunchedEffect(currentDate) {
        snapshotFlow { currentDate }
            .distinctUntilChanged()
            .collect { calendarViewModel.setSelectedDate(it) }
    }

    val startOfWeek = remember(currentDate) { currentDate.with(DayOfWeek.MONDAY) }
    val daysOfWeek = remember(startOfWeek) { (0..5).map { startOfWeek.plusDays(it.toLong()) } }

    Column(modifier = Modifier.fillMaxSize()) {

        WeekHeader(
            days = daysOfWeek,
            selectedDate = selectedDate,
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
//            beyondBoundsPageCount = 2, // рендерим только соседние страницы
            modifier = Modifier.weight(1f).fillMaxWidth(),
            key = { it } // помогает повторно использовать страницы
        ) { page ->
            val pageDate = pageToDate(initialPage, page, baseDate)
            ScheduleContent(
                date = pageDate,
                titleStyle = titleStyle,
                titleFont = titleFont,
                calendarViewModel = calendarViewModel,
                taskViewModel = taskViewModel
            )
        }
    }
}
