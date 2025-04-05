package com.example.universityschedule.castom_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PriorityButtons(
    selectedPriority: String,
    onPrioritySelected: (String) -> Unit,
    enabled: Boolean = true // Новый параметр для управления состоянием
) {
    val priorities = listOf("Low", "Medium", "High")

    val priorityColors = mapOf(
        "Low" to Color(0xFF4CAF50),
        "Medium" to Color(0xFFFFC107),
        "High" to Color(0xFFF44336)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp) //промежутки между чипами
    ) {
        priorities.forEach { priority ->
            val isSelected = priority == selectedPriority

            FilterChip(
                selected = isSelected,
                onClick = { if (enabled) onPrioritySelected(priority) },
                label = {
                    Text(
                        text = priority,
                        color = when {
                            !enabled ->
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f) //полупрозрачный
                            isSelected ->
                                Color.White
                            else ->
                                MaterialTheme.colorScheme.onSurface // обычный цвет текста
                        }
                    )
                },
                enabled = enabled, // Передаем состояние активности
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = priorityColors[priority] ?: Color.Gray,
                    selectedLabelColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                ),
                border = FilterChipDefaults.filterChipBorder(
                    selected = isSelected,
                    enabled = enabled,
                    borderColor = when {
                        !enabled && !isSelected -> (priorityColors[priority] ?: Color.Gray).copy(alpha = 0.5f)
                        !enabled && isSelected -> Color.Transparent
                        isSelected -> Color.Transparent
                        else -> priorityColors[priority] ?: MaterialTheme.colorScheme.outline
                    }
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}