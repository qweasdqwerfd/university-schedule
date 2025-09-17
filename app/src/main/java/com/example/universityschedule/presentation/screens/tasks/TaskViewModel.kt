package com.example.universityschedule.presentation.screens.tasks

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel(), TaskDialogController {
    private val _navigation = Channel<Screen>()
    val navigation = _navigation.receiveAsFlow()


    val itemsList = repository.getItems()

    override var dialogTitle = mutableStateOf("")
    override var dialogDescription = mutableStateOf("")
    override var dialogDueDate = mutableStateOf("")
    override var dialogPriority = mutableStateOf(Priority.MEDIUM)
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
                        lessons = dialogRelatedLesson.value
                    )

                    repository.insert(task)

                    navigateTo(Screen.TASKS)
                }
            }

            DialogEvent.OnCancel -> {
                navigateTo(Screen.TASKS)
            }

            is DialogEvent.OnItemClick -> {
                navigateTo(Screen.TASK_DETAILS)
            }

            is DialogEvent.onFABClick -> {
                navigateTo(Screen.ADD_TASK)
            }
        }
    }

    fun navigateTo(screen: Screen) {
        viewModelScope.launch {
            _navigation.send(screen)
        }
    }

}