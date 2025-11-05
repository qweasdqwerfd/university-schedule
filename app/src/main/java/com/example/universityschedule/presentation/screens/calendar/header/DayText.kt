package com.example.universityschedule.presentation.screens.calendar.header

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayText(
    date: LocalDate,
    isSelected: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    colorText: Color,
    fontText: FontWeight
) {
    val today = LocalDate.now()
    val isToday = date == today

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
                    .background(Color(0xFF3F51B5)), // заливка
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    color = Color.White,
                    fontWeight = fontText,
                    maxLines = 1
                )
            }
        }
        else if (isToday) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF3F51B5), CircleShape), // синяя рамка
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    color = colorText,
                    fontWeight = fontText,
                    maxLines = 1
                )
            }
        }
        else {
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
