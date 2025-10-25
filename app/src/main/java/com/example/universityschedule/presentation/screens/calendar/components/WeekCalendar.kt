package com.example.universityschedule.presentation.screens.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun WeekHeader(
    days: List<LocalDate>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    colorText: Color = MaterialTheme.colorScheme.onSurface,
    fontText: FontWeight = FontWeight.Bold
) {
    var currentStart by remember { mutableStateOf(days.first()) }
    var currentDays by remember { mutableStateOf(days) }
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(days) {
        currentStart = days.first()
        currentDays = days
        offsetX.snapTo(0f)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            when {
                                offsetX.value > 150 -> { // свайп вправо → предыдущая неделя
                                    // простая анимация "ухода" и возврата
                                    offsetX.animateTo(1000f, tween(200))
                                    currentStart = currentStart.minusWeeks(1)
                                    currentDays = (0..5).map { currentStart.plusDays(it.toLong()) }
                                    // уведомляем родителя — например, о новом старте недели (понедельник)
                                    onDateSelected(currentStart)
                                    // подготавливаем "вход" и плавно возвращаемся
                                    offsetX.snapTo(-1000f)
                                    offsetX.animateTo(0f, tween(200))
                                }

                                offsetX.value < -150 -> { // свайп влево → следующая неделя
                                    offsetX.animateTo(-1000f, tween(200))
                                    currentStart = currentStart.plusWeeks(1)
                                    currentDays = (0..5).map { currentStart.plusDays(it.toLong()) }
                                    onDateSelected(currentStart)
                                    offsetX.snapTo(1000f)
                                    offsetX.animateTo(0f, tween(200))
                                }

                                else -> {
                                    offsetX.animateTo(0f, tween(150))
                                }
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        coroutineScope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetX.value.toInt(), 0) }
                .padding(vertical = 8.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            currentDays.forEach { date ->
                DayText(
                    date = date,
                    isSelected = date == selectedDate,
                    onDateSelected = onDateSelected,
                    colorText = colorText,
                    fontText = fontText
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DayText(
    date: LocalDate,
    isSelected: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    colorText: Color,
    fontText: FontWeight
) {
    Column(
        modifier = Modifier
            .clickable { onDateSelected(date) }
            .padding(horizontal = 4.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3F51B5)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    color = Color.White,
                    fontWeight = fontText,
                    maxLines = 1
                )
            }
        } else {
            Text(
                text = date.dayOfWeek.getDisplayName(
                    java.time.format.TextStyle.SHORT,
                    Locale("ru")
                ),
                color = colorText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = date.dayOfMonth.toString(),
                color = colorText,
                fontWeight = fontText,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = date.month.getDisplayName(
                    java.time.format.TextStyle.SHORT_STANDALONE,
                    Locale("ru")
                ),
                color = colorText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
        }
    }
}
