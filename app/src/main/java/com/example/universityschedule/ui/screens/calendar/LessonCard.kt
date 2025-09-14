package com.example.universityschedule.ui.screens.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.universityschedule.Dimens.MediumPadding1
import com.example.universityschedule.Dimens.MediumShape1
import com.example.universityschedule.Dimens.SmallElevation
import com.example.universityschedule.Dimens.SmallPadding1

@Composable
fun LessonCard(
    lesson: Lesson,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(MediumShape1),
        elevation = CardDefaults.cardElevation(defaultElevation = SmallElevation)
    ) {
        Column(modifier = modifier.padding(MediumPadding1)) {
            Text(lesson.subject, style = textStyle)
            Spacer(modifier.height(SmallPadding1))
            Text("⏰ ${lesson.time}")
            Text("📍 ${lesson.place}")
            Text("👨‍🏫 ${lesson.teacher}")
        }
    }
}