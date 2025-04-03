package com.example.universityschedule.top_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {

    var currentRoute by remember { mutableStateOf("calendar") }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute = backStackEntry.destination.route ?: "Ресурсы"
        }
    }

    Column {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),

            title = {
                Text(
                    text =
                        when (currentRoute) {
                            "calendar" -> "University Schedule"
                            "tasks" -> "Tasks"
                            else -> {"Lessons"}
                        },

                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            },

            )

    }
}