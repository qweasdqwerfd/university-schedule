package com.example.universityschedule.presentation.screens.tasks

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.navigation.NavigationManager
import com.example.universityschedule.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val navigationManager: NavigationManager,
) : ViewModel(), TaskDialogController {

    val itemsList = repository.getItems()

    override var dialogTitle = mutableStateOf("")
    override var dialogDescription = mutableStateOf("")
    override var dialogDueDate = mutableStateOf("")
    override var dialogPriority = mutableStateOf(Priority.Medium)
    override var dialogRelatedLesson = mutableStateOf(LessonChip.NONE)


    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            DialogEvent.OnConfirm -> {
                viewModelScope.launch {
                    val task = TaskItem(
                        title = dialogTitle.value,
                        description = dialogDescription.value,
                        dueDate = dialogDueDate.value,
                        priority = dialogPriority.value,
                        lessons = dialogRelatedLesson.value,
                        check = false
                    )

                    repository.insert(task)

                    navigationManager.navigateTo(Screen.TASKS.route)

                    dialogTitle.value = ""
                    dialogDescription.value = ""
                    dialogDueDate.value = ""
                    dialogPriority.value = Priority.Medium
                    dialogRelatedLesson.value = LessonChip.NONE


                }
            }

            DialogEvent.OnCancel -> {
                viewModelScope.launch {
                    navigationManager.navigateTo(Screen.TASKS.route)
                }

            }

            is DialogEvent.OnItemClick -> {
                viewModelScope.launch {
                    navigationManager.navigateTo(Screen.TASK_DETAILS.createRoute(event.id))
                }
            }

            is DialogEvent.OnFABClick -> {
                viewModelScope.launch {
                    navigationManager.navigateTo(Screen.ADD_TASK.route)
                }
            }
            is DialogEvent.OnCircleClick -> {
                viewModelScope.launch {
                    val currentTask = repository.getItemById(event.id)
                    currentTask.let {
                        repository.insert(it.copy(check = !it.check))
                    }
                }
            }
        }
    }


}