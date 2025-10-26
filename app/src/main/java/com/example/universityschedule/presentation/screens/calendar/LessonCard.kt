package com.example.universityschedule.presentation.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Brush
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.IconSpec
import com.example.universityschedule.presentation.common.components.IconTopButton
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Displayable

@Composable
fun LessonCard(
    title: String,
    time: String,
    location: String,
    teacher: String,
    type: LessonType,
    modifier: Modifier = Modifier
) {
    val corner = 14.dp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(corner),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // чтобы полоска растягивалась по высоте
        ) {
            // Левый градиент/полоска
            val stripeColors = listOf(
                lessonTypeData(type).third.copy(alpha = 1f),
                lessonTypeData(type).third.copy(alpha = 0.85f)
            )
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(stripeColors),
                        shape = RoundedCornerShape(topStart = corner, bottomStart = corner)
                    )
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(14.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // init
                        val (label, iconSpec, color) = lessonTypeData(type)
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = color.copy(alpha = 0.12f),
                            tonalElevation = 0.dp,
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(28.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            ) {

                                IconTopButton(
                                    iconSpec = iconSpec,
                                    contentDescription = label,
                                    size = when (type) {
                                        LessonType.Lecture -> 16.dp
                                        LessonType.Practice -> 18.dp
                                        LessonType.Lab -> 18.dp
                                        LessonType.Exam -> 18.dp
                                        LessonType.Coursework -> 16.dp
                                        LessonType.CourseProject -> 16.dp
                                        LessonType.Attestation -> 16.dp
                                        LessonType.InterimCertification -> 16.dp
                                        LessonType.RGZ -> 16.dp
                                        LessonType.IDZ -> 16.dp
                                        LessonType.SupervisionOfSelfEmployment -> 16.dp
                                        LessonType.SetOff -> 18.dp
                                        LessonType.DifferentialSet -> 18.dp
                                        LessonType.Consultation -> 18.dp
                                        LessonType.CurrentConsultation -> 18.dp

                                    },
                                    tint = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = color,
                                        fontWeight = FontWeight.Medium
                                    )
                                )

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Метаданные: время
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Time",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = time,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Место
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = location,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Преподаватель
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Teacher",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = teacher,
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}


@Preview
@Composable
private fun LessonCardPreview() {

    Column {
        LessonCard(
            title = "Computer Science",
            time = "14:00 — 15:30",
            location = "Tech Building, Room 305",
            teacher = "Prof. Williams",
            type = LessonType.Practice,
            modifier = Modifier.padding(8.dp)
        )
    }

}