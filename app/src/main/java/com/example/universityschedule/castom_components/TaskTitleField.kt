package com.example.universityschedule.castom_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskTitleField(
    onTitleChanged: (String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = title,
        onValueChange = {
            title = it
            onTitleChanged(it)
            isError = it.isBlank()
        },
        label = { Text("Enter task title") },
        placeholder = { Text("Enter task title") },
        modifier = Modifier
            .fillMaxWidth()
            .offset(y=3.dp),
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text("Title cannot be empty", color = MaterialTheme.colorScheme.error)
            }
        }
    )
}