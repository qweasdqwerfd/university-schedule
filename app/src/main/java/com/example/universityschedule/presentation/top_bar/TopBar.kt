package com.example.universityschedule.presentation.top_bar

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.custom_components.IconButton.CancelIconButton
import com.example.universityschedule.presentation.custom_components.IconButton.SaveIconButton
import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.details.DetailsEvent
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    taskViewModel: TaskViewModel = hiltViewModel(),
    taskDetailsViewModel: TaskDetailsViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""



    Column {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),

            title = {
                Text(
                    text = when (currentRoute) {
                        Screen.CALENDAR.route -> "University Schedule"
                        Screen.TASKS.route -> "Tasks"
                        Screen.ADD_TASK.route -> "Add New Task"
                        else -> "Lessons"
                    },
                    modifier = when (currentRoute) {
                        Screen.ADD_TASK.route -> Modifier.padding(start = 10.dp)
                        else -> Modifier
                    },
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                if (currentRoute == Screen.ADD_TASK.route) {
                    SaveIconButton {
                        taskViewModel.onDialogEvent(DialogEvent.OnConfirm)
                    }
                }
            },
            navigationIcon = {
                if (currentRoute == Screen.ADD_TASK.route) {
                    CancelIconButton {
                        taskViewModel.onDialogEvent(DialogEvent.OnCancel)
                    }
                }
                if (currentRoute == Screen.TASK_DETAILS.route) {
                    CancelIconButton {
                        taskDetailsViewModel.onDialogEvent(DetailsEvent.OnCancel)
                    }
                }
            }


        )

    }
}