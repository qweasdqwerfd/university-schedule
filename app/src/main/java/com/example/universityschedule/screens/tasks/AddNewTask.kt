package com.example.universityschedule.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universityschedule.castom_components.DefaultTextField
import com.example.universityschedule.castom_components.DescriptionTextField
import com.example.universityschedule.castom_components.IconTextField
import com.example.universityschedule.castom_components.PriorityButtons

@Preview(showBackground = true)
@Composable
fun AddNewTask() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 17.dp, top = 15.dp, end = 17.dp)

    ) {
        Text(

            text = "Title",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        DefaultTextField {}

        Text(
            text = "Description",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        DescriptionTextField{}

        Text(
            text = "Due Date",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        IconTextField{}

        Text(
            text = "Priority",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        var selectedPriority by remember { mutableStateOf("Medium") }

        PriorityButtons(
            selectedPriority = selectedPriority,
            onPrioritySelected = { priority ->
                selectedPriority = priority
            }
        )








    }
}