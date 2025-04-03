package com.example.universityschedule.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universityschedule.castom_components.TaskTitleField

@Preview(showBackground = true)
@Composable
fun AddNewTask() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 15.dp, end = 10.dp)

    ) {
        Text(

            text = "Title",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        TaskTitleField {}

        Text(
            text = "Description",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )




    }
}