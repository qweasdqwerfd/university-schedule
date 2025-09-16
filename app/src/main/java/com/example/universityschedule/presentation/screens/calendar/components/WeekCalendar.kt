package com.example.universityschedule.presentation.screens.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.universityschedule.presentation.Dimens.LargePadding1
import com.example.universityschedule.presentation.Dimens.MediumPadding3
import com.example.universityschedule.presentation.Dimens.MediumPadding4
import com.example.universityschedule.presentation.Dimens.MediumPadding5
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(days.size) { index ->
            val date = days[index]
            val isSelected = date == selectedDate

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(vertical = MediumPadding3, horizontal = MediumPadding5)
                    .clickable { onDateSelected(date) }
            ) {
                if (isSelected) {
                    Box(
                        modifier = modifier
                            .size(LargePadding1)
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
                        text = date.dayOfWeek
                            .getDisplayName(TextStyle.SHORT, Locale("ru")),
                        color = colorText
                    )
                    Spacer(modifier.height(MediumPadding4))
                    Text(
                        text = date.dayOfMonth.toString(),
                        color = colorText,
                        fontWeight = fontText

                    )
                    Spacer(modifier.height(MediumPadding4))
                    Text(
                        text = date.month
                            .getDisplayName(TextStyle.SHORT_STANDALONE, Locale("ru")),
                        color = colorText
                    )
                }

            }

        }
    }
}

