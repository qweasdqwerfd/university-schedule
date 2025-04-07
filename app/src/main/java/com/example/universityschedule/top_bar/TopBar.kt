package com.example.universityschedule.top_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.universityschedule.castom_components.IconButton.CancelIconButton
import com.example.universityschedule.castom_components.IconButton.SaveIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {

    var currentRoute by remember { mutableStateOf("calendar") }
    var previousRoute by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            previousRoute = currentRoute
            currentRoute = backStackEntry.destination.route ?: "Ресурсы"

        }

    }

    Column {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),

            title = {
                Text(
                    text = when (currentRoute) {
                        "calendar" -> "University Schedule"
                        "tasks" -> "Tasks"
                        "addNewTask" -> "Add New Task"
                        else -> "Lessons"
                    },
                    modifier = when (currentRoute) {
                        "addNewTask" -> Modifier.padding(start = 10.dp)
                        else -> Modifier
                    },
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                if (currentRoute == "addNewTask") {
                    SaveIconButton {
                        previousRoute?.let { navController.navigate(it) }

                    }

                }
            },
            navigationIcon = {
                if (currentRoute == "addNewTask") {
                    CancelIconButton {
                        previousRoute?.let { navController.navigate(it) }
                    }

                }
            }


        )

    }
}