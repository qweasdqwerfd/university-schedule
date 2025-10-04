package com.example.universityschedule.presentation.screens.tasks.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universityschedule.R

@Composable
fun PriorityChipLarge(
    priority: Priority,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = mapOf(
        Priority.Low to Color(0xFF4CAF50),
        Priority.Medium to Color(0xFF506EA8),
        Priority.High to Color(0xFFF44336)
    )

    val containerColor = colors[priority]!!

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.warn),
                    contentDescription = null,
                    tint = if (isSelected) Color.White else containerColor,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = priority.name,
                    fontSize = 16.sp,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = containerColor,
            selectedLabelColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(36.dp)
    )
}
