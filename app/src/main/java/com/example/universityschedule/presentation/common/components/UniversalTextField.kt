package com.example.universityschedule.presentation.common.components

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
import androidx.compose.ui.unit.Dp
import com.example.universityschedule.R

@Composable
fun UniversalTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    minHeight: Dp = Dp.Unspecified,
    maxHeight: Dp = Dp.Unspecified,
    leadingIcon: @Composable (() -> Unit)? = null,
    errorMessage: String = "Field cannot be empty",
    maxChars: Int = Int.MAX_VALUE,
) {
    val focusManager = LocalFocusManager.current
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxChars) {
                onValueChange(it)
                isError = it.isBlank()
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        label = { Text(label, color = colorResource(R.color.textFieldFont)) },
        placeholder = { Text(placeholder) },
        leadingIcon = leadingIcon,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight, max = maxHeight),
        singleLine = singleLine,
        maxLines = maxLines,
        isError = isError,
        supportingText = {
            when {
                isError -> Text(errorMessage, color = MaterialTheme.colorScheme.error)
                maxChars != Int.MAX_VALUE -> Text("${value.length} / $maxChars")
            }
        }
    )
}
