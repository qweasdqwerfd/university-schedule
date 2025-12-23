package com.example.universityschedule.presentation.screens.tasks

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.domain.model.TaskItemEntity
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.domain.usecases.FABUseCase
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.common.dialog_controller.DialogController
import com.example.universityschedule.presentation.common.dialog_controller.LessonChip
import com.example.universityschedule.presentation.common.dialog_controller.Priority
import com.example.universityschedule.presentation.common.snack_bar.SnackBarType
import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val uiManager: UIManager,
    private val dialogController: DialogController,
    private val fabUseCase: FABUseCase
) : ViewModel(), DialogController by dialogController {

    override var dialogTitle = mutableStateOf("")
    override var dialogDescription = mutableStateOf("")

    @SuppressLint("NewApi")
    override var dialogDueDate = mutableStateOf<LocalDateTime?>(null)
    override var dialogPriority = mutableStateOf(Priority.Medium)
    override var dialogRelatedLesson = mutableStateOf(LessonChip.NONE)

    private val _sortByPriority = MutableStateFlow(false)
    val sortByPriority = _sortByPriority.asStateFlow()

    val itemsList = repository.getItems().map { tasks -> tasks.reversed() }

    val sortedTasks = combine(itemsList, sortByPriority) { tasks, sort ->
        if (sort) {
            tasks.sortedByDescending { it.priority }                                                // descending sort
        } else {
            tasks
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleSort() {
        _sortByPriority.value = !_sortByPriority.value
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            DialogEvent.OnConfirm -> {
                val title = dialogTitle.value.trim()
                val description = dialogDescription.value.trim()
                val dueDate = dialogDueDate.value

                if (title.isEmpty() || dialogDueDate.value == null) {
                    viewModelScope.launch {
                        uiManager.sendSnackBar(
                            message = "Не все поля заполнены!",
                            type = SnackBarType.WARNING,
                            duration = SnackbarDuration.Short
                        )
                    }
                    return
                }
                viewModelScope.launch {
                    val task = if (description.isEmpty()) {
                        TaskItemEntity(
                            title = title,
                            description = "",
                            dueDate = dueDate!!,
                            priority = dialogPriority.value,
                            lessons = dialogRelatedLesson.value,
                            check = false
                        )
                    } else {
                        TaskItemEntity(
                            title = title,
                            description = description,
                            dueDate = dueDate!!,
                            priority = dialogPriority.value,
                            lessons = dialogRelatedLesson.value,
                            check = false
                        )
                    }
                    repository.insert(task)
                    uiManager.navigateBack()

                    dialogTitle.value = ""
                    dialogDescription.value = ""
                    dialogDueDate.value = null
                    dialogPriority.value = Priority.Medium
                    dialogRelatedLesson.value = LessonChip.NONE
                }
            }


            DialogEvent.OnCancel -> {
                viewModelScope.launch {
                    uiManager.navigateBack()
                }

            }

            is DialogEvent.OnItemClick -> {
                viewModelScope.launch {
                    uiManager.navigateTo(Screen.TASK_DETAILS.createRoute(event.id))
                }
            }

            is DialogEvent.OnFABClick -> {
                viewModelScope.launch {
                    fabUseCase.invoke()
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

            else -> {}
        }
    }


}