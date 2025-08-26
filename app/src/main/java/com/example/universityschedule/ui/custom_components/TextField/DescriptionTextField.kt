package com.example.universityschedule.ui.custom_components.TextField

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R

@Composable
fun DescriptionTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            isError = it.isBlank()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        maxLines = 5,  // Максимум видимых строк
        label = { Text(
            text = "Enter task description",
            color = colorResource(R.color.textFieldFont)
            ) },
        placeholder = { Text("Enter task description") },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp, max = 300.dp)
            ,
        singleLine = false, //многострочный ввод
        isError = isError,
        supportingText = {
            if (isError) {
                Text("Title cannot be empty", color = MaterialTheme.colorScheme.error)
            }
        }
    )


}