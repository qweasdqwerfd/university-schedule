package com.example.universityschedule.presentation.screens.calendar.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.universityschedule.presentation.common.components.MainDivider
import com.example.universityschedule.presentation.util.Constants.formatterForLocalTime
import com.example.universityschedule.presentation.util.dimens
import java.time.LocalDate
import androidx.compose.ui.text.TextStyle as ComposeTextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TitleDate(
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    textStyle: ComposeTextStyle = MaterialTheme.typography.titleMedium,
    textWeight: FontWeight = FontWeight.ExtraBold,
) {


    MainDivider()
    Text(
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimens.space20)
        ,
        text = formatterForLocalTime.format(selectedDate)
            .replaceFirstChar { it.uppercase() },
        style = textStyle,
        fontWeight = textWeight
    )
    MainDivider()


}