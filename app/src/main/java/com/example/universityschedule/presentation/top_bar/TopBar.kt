package com.example.universityschedule.presentation.top_bar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.common.components.DetailsButton
import com.example.universityschedule.presentation.common.components.IconSpec
import com.example.universityschedule.presentation.common.components.IconTopButton
import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.groups.GroupViewModel
import com.example.universityschedule.presentation.screens.calendar.CalendarViewModel
import com.example.universityschedule.presentation.screens.search.SearchViewModel
import com.example.universityschedule.presentation.screens.tasks.TaskViewModel
import com.example.universityschedule.presentation.screens.tasks.details.CRUDEvent
import com.example.universityschedule.presentation.screens.tasks.details.TaskDetailsViewModel
import com.example.universityschedule.presentation.util.dimens
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    taskViewModel: TaskViewModel = hiltViewModel(),
    taskDetailsViewModel: TaskDetailsViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel(),
    groupViewModel: GroupViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""


    val sortByPriority by taskViewModel.sortByPriority.collectAsState()
    val rotation by animateFloatAsState(
        targetValue = if (sortByPriority) 180f else 0f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    val selectedGroup by groupViewModel
        .selectedGroup
        .collectAsStateWithLifecycle()

    Column {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),

            title = {
                when (currentRoute) {
                    Screen.CALENDAR.route -> {
                        if (selectedGroup?.institute == null) {
                            Text(text = "")
                        } else {
                            Text(text = selectedGroup?.institute.toString())
                        }
                    }

                    Screen.TASKS.route -> Text("Задачи")
                    Screen.ADD_TASK.route -> Text("Добавить задачу")
                    Screen.TASK_DETAILS.route -> Text("Детали задачи")
                    Screen.SEARCH_SCHEDULE.route -> Text("Поиск")
                    else -> Text("Пары")
                }
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
                if (currentRoute == Screen.CALENDAR.route) {
                    DetailsButton(
                        content = {
                            if (selectedGroup?.name == null) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    text = selectedGroup?.name
                                        ?.replace("(1)", "")
                                        ?.replace("(2)", "").toString()
                                )
                            }
                        },
                        color = colorResource(R.color.selectedBottom),
                        onClick = {
                            searchViewModel.onDialogEvent(DialogEvent.OnButtonClick)
                        },
                        icon = null,
                        sizeIcon = null,
                        text = null
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
                            taskDetailsViewModel.onCRUDEvent(CRUDEvent.OnCancel)
                        },
                        iconSpec = IconSpec.Vector(Icons.Default.ArrowBack),
                        contentDescription = "back"
                    )
                }
                if (currentRoute == Screen.SEARCH_SCHEDULE.route) {
                    IconTopButton(
                        onClick = {
                            searchViewModel.onDialogEvent(DialogEvent.OnCancel)
                        },
                        iconSpec = IconSpec.Vector(Icons.Default.ArrowBack),
                        contentDescription = "back"
                    )
                }
            }


        )

    }
}