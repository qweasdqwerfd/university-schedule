package com.example.universityschedule.presentation.common.components

import android.annotation.SuppressLint
import android.util.Size
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconTopButton(
    onClick: () -> Unit,
    icon: Any,
    contentDescription: String = "",
    enabled: Boolean = true,
    size: Dp = Dp.Unspecified,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        when (icon) {
            is ImageVector -> {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            is Painter -> {
                Icon(
                    painter = icon,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .size(size)
                    ,

                )
            }

            else -> throw IllegalArgumentException("Unsupported icon type")
        }
    }
}