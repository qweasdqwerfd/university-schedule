package com.example.universityschedule.ui.screens.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import com.example.universityschedule.ui.custom_components.MainDivider
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.ui.text.TextStyle as ComposeTextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TitleDate(
    selectedDate: LocalDate,
    textStyle: ComposeTextStyle = MaterialTheme.typography.titleMedium,
    textWeight: FontWeight = FontWeight.ExtraBold
) {
    MainDivider()

    val formatter = remember {
        DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale("ru"))
    }

    Text(
        text = formatter.format(selectedDate)
            .replaceFirstChar { it.uppercase() },
        style = textStyle,
        fontWeight = textWeight
    )

    MainDivider()

}