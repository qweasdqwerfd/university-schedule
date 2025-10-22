package com.example.universityschedule.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
inline fun <reified T : Enum<T>> EnumChipsRow(
    selectedItem: T,
    crossinline onItemSelected: (T) -> Unit,
    crossinline chipContent: @Composable (item: T, isSelected: Boolean, onClick: () -> Unit) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(enumValues<T>().toList()) { item ->
            val isSelected = item == selectedItem
            chipContent(item, isSelected, { onItemSelected(item) })
        }
    }
}

