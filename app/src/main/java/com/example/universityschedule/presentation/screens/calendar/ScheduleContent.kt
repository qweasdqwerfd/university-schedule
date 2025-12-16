package com.example.universityschedule.presentation.screens.calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.R
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.domain.usecases.GetLessonsUseCase
import com.example.universityschedule.presentation.screens.calendar.components.LessonCard
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.components.CardTaskPanel
import com.example.universityschedule.presentation.util.dimens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleContent(
    date: LocalDate,
    titleStyle: TextStyle,
    titleFont: FontWeight,
    taskViewModel: TaskViewModel,
    calendarViewModel: CalendarViewModel,
) {

    val context = LocalContext.current
    val userPrefsRepository = remember {
        UserPrefsRepository(context)
    }

    val selectedGroupId by userPrefsRepository.selectedGroupId.collectAsState(initial = null)



    LaunchedEffect(date, selectedGroupId) {
        Log.d("Calendar", "LaunchedEffect run: date=$date group=$selectedGroupId")
        if (selectedGroupId == null) return@LaunchedEffect
        calendarViewModel.onPageChanged(date)
    }





    val isLoading by calendarViewModel.isLoadingCurrentWeek.collectAsState()
    val lessonsByDate by calendarViewModel.lessonsByDate.collectAsState()
    val lessonsForToday = lessonsByDate[date].orEmpty()
    val sortedLessons = remember(lessonsForToday) { lessonsForToday.sortedBy { it.startTime } }

    val tasks by taskViewModel.itemsList.collectAsState(emptyList())
    val tasksForToday = tasks.filter { it.dueDate.toLocalDate() == date }

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

        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else {
            items(sortedLessons, key = { it.id!! }) { lesson ->
                LessonCard(
                    title = lesson.subjectName.toString(),
                    startTime = lesson.startTime.toString(),
                    endTime = lesson.endTime.toString(),
                    location = lesson.location,
                    teacher = lesson.teacher,
                    type = lesson.type
                )
                Spacer(Modifier.height(MaterialTheme.dimens.space8))
            }

            if (sortedLessons.isEmpty()) {
                item {
                    Spacer(Modifier.height(MaterialTheme.dimens.space32))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit1),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(Modifier.height(MaterialTheme.dimens.space20))
                        Text(text = "Нет пар на этот день", style = titleStyle)
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(MaterialTheme.dimens.space36))
            Text(
                text = "Задания на сегодня",
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space20),
                style = titleStyle,
                fontWeight = titleFont
            )
        }

        if (tasksForToday.isNotEmpty()) {
            items(tasksForToday, key = { it.id }) { task ->
                CardTaskPanel(
                    item = task,
                    onEvent = { taskViewModel.onDialogEvent(it) }
                )
            }
        } else {
            item {
                Spacer(Modifier.height(MaterialTheme.dimens.space32))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit1),
                        contentDescription = "edit",
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF3F51B5)
                    )
                    Spacer(Modifier.height(MaterialTheme.dimens.space20))
                    Text(text = "Нет задач на этот день", style = titleStyle)
                }
            }
        }
    }
}