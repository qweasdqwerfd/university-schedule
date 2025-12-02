package com.example.universityschedule.presentation.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class IconSpec {
    data class Vector(val imageVector: ImageVector) : IconSpec()
    data class PainterRes(@DrawableRes val resId: Int) : IconSpec()
    data class PainterObj(val painter: Painter) : IconSpec()
}

@Composable
fun IconTopButton(
    onClick: (() -> Unit)? = null,
    iconSpec: Any,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    size: Dp = Dp.Unspecified,
    buttonSize: Dp = size
) {
    @Composable
    fun RenderIcon() {
        when (iconSpec) {
            is IconSpec.Vector -> {
                Icon(
                    imageVector = iconSpec.imageVector,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(size),
                    tint = tint,
                )
            }
            is IconSpec.PainterRes -> {
                val painter = painterResource(id = iconSpec.resId)
                Icon(
                    painter = painter,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(size),
                    tint = tint
                )
            }
            is IconSpec.PainterObj -> {
                Icon(
                    painter = iconSpec.painter,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(size),
                    tint = tint
                )
            }
        }
    }

    if (onClick != null) {

        IconButton(
            onClick = onClick,
            modifier = modifier.size(buttonSize)
        ) {
            RenderIcon()
        }

    } else {
        Box(
            modifier = modifier.size(buttonSize),
            contentAlignment = Alignment.Center
        ) {
            RenderIcon()
        }
    }
}
