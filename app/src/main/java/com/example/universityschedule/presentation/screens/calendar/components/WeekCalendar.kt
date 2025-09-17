package com.example.universityschedule.presentation.screens.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.universityschedule.presentation.util.dimens
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun WeekCalendar(
    days: List<LocalDate>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    colorText: Color = MaterialTheme.colorScheme.onSurface,
    fontText: FontWeight = FontWeight.Bold
) {
    LazyRow(
        modifier = modifier.padding(vertical = MaterialTheme.dimens.space8),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(days.size) { index ->
            val date = days[index]
            val isSelected = date == selectedDate

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clickable { onDateSelected(date) }
                    .padding(horizontal = MaterialTheme.dimens.space8)
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.space48)
                            .clip(CircleShape)
                            .background(Color(0xFF3F51B5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            color = Color.White,
                            fontWeight = fontText
                        )
                    }
                } else {
                    Text(
                        text = date.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale("ru")),
                        color = colorText
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.space10))
                    Text(
                        text = date.dayOfMonth.toString(),
                        color = colorText,
                        fontWeight = fontText
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.space10))
                    Text(
                        text = date.month.getDisplayName(java.time.format.TextStyle.SHORT_STANDALONE, Locale("ru")),
                        color = colorText
                    )
                }
            }
        }
    }
}