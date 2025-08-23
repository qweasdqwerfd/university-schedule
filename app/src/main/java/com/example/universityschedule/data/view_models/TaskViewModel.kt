package com.example.universityschedule.data.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.entities.TaskItem
import com.example.universityschedule.data.repo.TaskRepository
import com.example.universityschedule.data.view_models.contracts.controllers.Lesson
import com.example.universityschedule.data.view_models.contracts.controllers.Priority
import com.example.universityschedule.data.view_models.contracts.controllers.TaskDialogController
import com.example.universityschedule.data.view_models.contracts.events.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel(), TaskDialogController {
    override var dialogTitle = mutableStateOf("")
        private set
    override val dialogDescription = mutableStateOf("")
        private set
    override val dialogDueDate = mutableStateOf("")
        private set
    override val dialogPriority = mutableStateOf(Priority.MEDIUM)
        private set
    override val dialogRelatedLesson = mutableStateOf(Lesson.NONE)
        private set


    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            DialogEvent.OnConfirm -> {
                viewModelScope.launch { 
                    repository.insert(
                        TaskItem(
                            id = TODO(),
                            priority = TODO(),
                            lessons = TODO(),
                            title = TODO(),
                            description = TODO(),
                            dueDate = TODO()
                        )
                    )
                }
            }

            DialogEvent.OnCancel -> {

            }
        }
    }
}