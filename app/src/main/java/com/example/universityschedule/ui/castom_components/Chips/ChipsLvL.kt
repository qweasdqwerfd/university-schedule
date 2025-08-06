package com.example.universityschedule.ui.castom_components.Chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universityschedule.R

@Composable
fun ChipsLvL(
    selectedPriority: String,
    onPrioritySelected: (String) -> Unit,
    enabled: Boolean = true,
) {
    val priorities = listOf("Low", "Medium", "High")
    val priorityColors = mapOf(
        "Low" to Color(0xFF4CAF50),
        "Medium" to Color(0xFF506EA8),
        "High" to Color(0xFFF44336)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // Равномерное распределение
    ) {
        priorities.forEach { priority ->
            val isSelected = priority == selectedPriority
            val containerColor = priorityColors[priority] ?: Color.Gray

            Box(
                modifier = Modifier.weight(1f), // Равные доли ширины
                contentAlignment = Alignment.Center
            ) {
                FilterChip(
                    selected = isSelected,
                    onClick = { if (enabled) onPrioritySelected(priority) },
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.warn),
                                contentDescription = "priority",
                                tint = if (isSelected) Color.White else containerColor,
                                modifier = Modifier.size(12.dp)
                            )

                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = priority,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Visible
                            )
                        }
                    },
                    enabled = enabled,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = containerColor,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        selected = isSelected,
                        borderColor = if (isSelected) Color.Transparent else containerColor,
                        enabled = enabled
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.95f) // 95% ширины родителя
                        .height(36.dp)
                )
            }
        }
    }
}