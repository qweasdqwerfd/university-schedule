package com.example.universityschedule.presentation.screens.tasks.details.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universityschedule.presentation.screens.tasks.components.Priority

@Composable
fun PriorityChipCompact(
    priority: Priority,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = mapOf(
        Priority.Low to Color(0xFF4CAF50),
        Priority.Medium to Color(0xFFFFC107),
        Priority.High to Color(0xFFF44336)
    )

    val containerColor = colors[priority]!!

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = priority.name,
                fontSize = 14.sp,
                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = containerColor,
            selectedLabelColor = Color.White
        ),
        modifier = Modifier.height(32.dp)
    )
}
