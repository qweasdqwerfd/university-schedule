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
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Displayable
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Priority

@Composable
inline fun <reified T> PriorityChipCompact(
    item: T,
    isSelected: Boolean,
    noinline onClick: () -> Unit,
    noinline colorProvider: (T) -> Color
) where T : Enum<T>, T : Displayable {

    val containerColor = colorProvider(item)

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = item.displayName,
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
