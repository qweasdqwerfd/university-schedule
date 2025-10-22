package com.example.universityschedule.presentation.top_bar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.common.components.IconSpec
import com.example.universityschedule.presentation.common.components.IconTopButton
import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.details.DetailsEvent
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsViewModel
import com.example.universityschedule.presentation.util.dimens

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


    val sortByPriority by taskViewModel.sortByPriority.collectAsState()
    val rotation by animateFloatAsState(
        targetValue = if (sortByPriority) 180f else 0f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )


    Column {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),

            title = {
                Text(
                    text = when (currentRoute) {
                        Screen.CALENDAR.route -> "Расписание университета"
                        Screen.TASKS.route -> "Задачи"
                        Screen.ADD_TASK.route -> "Добавить задачу"
                        Screen.TASK_DETAILS.route -> "Детали задачи"
                        else -> "Пары"
                    },
                    modifier = when (currentRoute) {
                        Screen.ADD_TASK.route -> Modifier.padding(start = MaterialTheme.dimens.space10)
                        else -> Modifier
                    },
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                if (currentRoute == Screen.TASKS.route) {
                    IconTopButton(
                        onClick = {
                            taskViewModel.toggleSort()
                        },
                        iconSpec = IconSpec.PainterRes(R.drawable.voronka),
                        contentDescription = "voronka",
                        size = 20.dp,
                        modifier = Modifier.graphicsLayer {
                            rotationZ = rotation
                        },
                        buttonSize = 30.dp
                    )
                }
                if (currentRoute == Screen.ADD_TASK.route) {
                    IconTopButton(
                        onClick = {
                            taskViewModel.onDialogEvent(DialogEvent.OnConfirm)
                        },
//                        enabled =
//                            taskViewModel.dialogTitle.value.isNotBlank() &&
//                            taskViewModel.dialogDescription.value.isNotBlank() &&
//                            taskViewModel.dialogDueDate.value.isNotBlank(),
                        iconSpec = IconSpec.Vector(Icons.Default.Check),
                        contentDescription = "ok",
                    )
                }
            },
            navigationIcon = {
                if (currentRoute == Screen.ADD_TASK.route) {
                    IconTopButton(
                        onClick = {
                            taskViewModel.onDialogEvent(DialogEvent.OnCancel)
                        },
                        iconSpec = IconSpec.Vector(Icons.Default.ArrowBack),
                        contentDescription = "back",
                    )
                }
                if (currentRoute == Screen.TASK_DETAILS.route) {

                    IconTopButton(
                        onClick = {
                            taskDetailsViewModel.onBottomDialogEvent(DetailsEvent.OnCancel)
                        },
                        iconSpec = IconSpec.Vector(Icons.Default.ArrowBack),
                        contentDescription = "back"
                    )
                }
            }


        )

    }
}