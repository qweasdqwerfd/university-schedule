package com.example.universityschedule.presentation.common.snack_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UniversalSnackBarHost(
    snackBarHostState: SnackbarHostState
) {
    SnackbarHost(hostState = snackBarHostState) { data ->
        val type = data.visuals.actionLabel?.let {
            runCatching { SnackBarType.valueOf(it) }.getOrNull()
        } ?: SnackBarType.INFO

        val backgroundColor = when (type) {
            SnackBarType.SUCCESS -> Color(0xFF4CAF50)
            SnackBarType.ERROR -> Color(0xFFF44336)
            SnackBarType.WARNING -> Color(0xFFFF9800)
            SnackBarType.INFO -> Color(0xFF2196F3)
        }

        Snackbar(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .wrapContentWidth()
                .clip(MaterialTheme.shapes.large)
                .background(backgroundColor)
                .shadow(8.dp, MaterialTheme.shapes.large),
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = backgroundColor
        ) {
            Text(
                text = data.visuals.message,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}
