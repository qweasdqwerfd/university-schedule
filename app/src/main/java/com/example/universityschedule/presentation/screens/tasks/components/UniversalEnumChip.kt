package com.example.universityschedule.presentation.screens.tasks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Displayable
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Priority

@Composable
inline fun <reified T> UniversalEnumChip(
    item: T,
    isSelected: Boolean,
    noinline onClick: () -> Unit,
    icon: Int? = null,
    noinline colorProvider: (T) -> Color ,
    noinline labelProvider: (T) -> String = { it.displayName }
) where T : Enum<T>, T : Displayable {

    val containerColor = colorProvider(item)

    val chipColors = FilterChipDefaults.filterChipColors(
        containerColor = Color.Transparent,
        selectedContainerColor = containerColor,
        labelColor = MaterialTheme.colorScheme.onSurface,
        selectedLabelColor = Color.White
    )

    val chipBorder = FilterChipDefaults.filterChipBorder(
        selected = isSelected,
        borderColor = if (isSelected) Color.Transparent else containerColor,
        enabled = true
    )


    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = if (isSelected) Color.White else containerColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Text(
                    text = labelProvider(item),
                    fontSize = 15.sp,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = chipColors,
        border = chipBorder,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .defaultMinSize(minWidth = 90.dp)
            .height(38.dp)
    )
}
