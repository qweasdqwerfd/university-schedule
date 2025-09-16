package com.example.universityschedule.presentation.screens.calendar.components

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
import com.example.universityschedule.presentation.Dimens.MediumPadding1
import com.example.universityschedule.presentation.Dimens.MediumShape1
import com.example.universityschedule.presentation.Dimens.SmallElevation
import com.example.universityschedule.presentation.Dimens.SmallPadding1
import com.example.universityschedule.presentation.screens.lessons.Lesson

@Composable
fun LessonCard(
    lesson: Lesson,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding1)
        ,
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