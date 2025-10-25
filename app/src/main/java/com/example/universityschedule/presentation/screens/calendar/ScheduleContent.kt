package com.example.universityschedule.presentation.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.LessonCard
import com.example.universityschedule.presentation.common.LessonType
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.components.CardTaskPanel
import com.example.universityschedule.presentation.util.dimens
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleContent(
    date: LocalDate,
    titleStyle: TextStyle,
    titleFont: FontWeight,
    viewModelTasks: TaskViewModel = hiltViewModel()
) {

    val tasks = viewModelTasks.itemsList.collectAsState(listOf(null))

    val tasksForToday = tasks.value.filter { task ->
        task?.dueDate?.toLocalDate() == date
    }

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
                text = "Задания на сегодня",
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space20),
                style = titleStyle,
                fontWeight = titleFont
            )


        }

        if (tasksForToday.isNotEmpty()) {

            items(
                items = tasksForToday
            ) { task ->

                task?.let {
                    CardTaskPanel(
                        item = it,
                        onEvent = { viewModelTasks.onDialogEvent(it) },
                    )
                }

            }


        } else {

            item {

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
            }

        }

    }
}
