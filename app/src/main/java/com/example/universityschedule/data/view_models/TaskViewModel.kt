package com.example.universityschedule.data.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.entities.TaskItem
import com.example.universityschedule.data.repo.TaskRepository
import com.example.universityschedule.data.view_models.contracts.controllers.LessonChip
import com.example.universityschedule.data.view_models.contracts.controllers.Priority
import com.example.universityschedule.data.view_models.contracts.controllers.TaskDialogController
import com.example.universityschedule.data.view_models.contracts.events.DialogEvent
import com.example.universityschedule.ui.navigation.Screen
import com.example.universityschedule.ui.navigation.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,

    ) : ViewModel(), TaskDialogController {

    init {
        Log.d("TaskVM", "ViewModel created: ${this.hashCode()}")
    }

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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

                    navigateTo(Screen.TASKS.route)
                }
            }

            DialogEvent.OnCancel -> {
                navigateTo(Screen.TASKS.route)
            }

            is DialogEvent.OnItemClick -> {
                sendUIEvent(UIEvent.Navigate(event.route))
            }
        }
    }

    fun sendUIEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    private fun navigateTo(route: String) {
        sendUIEvent(UIEvent.Navigate(route))
    }
}