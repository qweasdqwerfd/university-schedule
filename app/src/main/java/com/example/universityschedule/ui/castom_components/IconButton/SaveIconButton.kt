package com.example.universityschedule.ui.castom_components.IconButton

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R

@Composable
fun SaveIconButton(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(24.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.tick),
            contentDescription = "Save",
            tint = Color.Blue
        )
    }
}