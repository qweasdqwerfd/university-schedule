package com.example.universityschedule.ui.custom_components.Chips

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R

@Composable
fun ChipsLessons(
    selectedLessons: String,
    selectLessons: (String) -> Unit,
    enabled: Boolean = true,
) {

    val lessonsList = listOf("None", "Calculus", "Physics", "Math", "Programming", "Sport")
    val lessonsColors = mapOf(
        "None" to Color.Blue,
        "Calculus" to Color(0xFFF0A62B),
        "Physics" to Color(0xFF31b947),
        "Math" to Color(0xFF24afb5),
        "Programming" to Color(0xFFf6c610),
        "Sport" to Color(0xFFca2244)
    )



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        lessonsList.forEach { lesson ->

            var isSelect = lesson == selectedLessons
            var containerColor = lessonsColors[lesson] ?: Color.Gray


            FilterChip(
                selected = isSelect,
                onClick = { selectLessons(lesson) },
                label = {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (lesson != "None") {
                            Icon(
                                painter = painterResource(R.drawable.lessons),
                                tint = if (isSelect) Color.White else containerColor,
                                contentDescription = "lessonIcon",
                                modifier = Modifier
                                    .size(15.dp)
                                    .offset(y=3.dp),
                            )
                            Spacer(Modifier.width(8.dp))
                        }


                        Text(lesson,
                            color = if (isSelect) Color.White else containerColor
                        )
                    }
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = containerColor,
                    selectedLabelColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(10.dp),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = enabled,
                    selected = isSelect,
                    borderColor = if (isSelect) Color.Transparent else containerColor
                )
            )
        }


    }


}