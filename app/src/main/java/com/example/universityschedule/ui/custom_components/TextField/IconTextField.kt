package com.example.universityschedule.ui.custom_components.TextField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R

@Composable
fun IconTextField(
    value: String,
    onValueChanged: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChanged(it)
            isError = it.isBlank()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        label = { Text(
            "Mon, Mar 31, 09:42 PM",
        ) },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.calendar),
                contentDescription = "calendar",
                Modifier.size(23.dp)
            )
        },
        placeholder = { Text("Enter task due date") },
        modifier = Modifier
            .fillMaxWidth()
        ,
        singleLine = false,
        isError = isError,
        supportingText = {
            if (isError) {
                Text("Title cannot be empty", color = MaterialTheme.colorScheme.error)
            }
        }
    )

}