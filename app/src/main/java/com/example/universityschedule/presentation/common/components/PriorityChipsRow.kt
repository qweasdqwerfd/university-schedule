package com.example.universityschedule.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Priority

@Composable
fun PriorityChipsRow(
    selectedPriority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    chipContent: @Composable (priority: Priority, isSelected: Boolean, onClick: () -> Unit) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Priority.entries.forEach { priority ->                                                      //(values)
            val isSelected = priority == selectedPriority
            chipContent(priority, isSelected) { onPrioritySelected(priority) }
        }
    }
}
