package com.example.universityschedule.presentation.screens.tasks.details

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.common.snack_bar.SnackBarType
import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.navigation.UIManager
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.LessonChip
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.Priority
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.TaskDialogController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val uiManager: UIManager,
    private val dialogController: TaskDialogController,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), TaskDialogController by dialogController {

    private val taskId: Int = savedStateHandle.get<Int>("taskId") ?: -1
    private val _task = MutableStateFlow<TaskItem?>(null)
    val task: StateFlow<TaskItem?> = _task

    override var dialogTitle = mutableStateOf("")
    override var dialogDescription = mutableStateOf("")
    override var dialogDueDate = mutableStateOf("")
    override var dialogPriority = mutableStateOf(Priority.Medium)
    override var dialogRelatedLesson = mutableStateOf(LessonChip.NONE)


    val listLessons = mutableStateOf(LessonChip.values().toList())
    var checkState = mutableStateOf(false)
    var isSheetOpen = mutableStateOf(false)
        private set


    init {
        viewModelScope.launch {
            _task.value = repository.getItemById(taskId)
            _task.value?.let { task ->
                dialogTitle.value = task.title
                dialogDescription.value = task.description
                dialogDueDate.value = task.dueDate
                dialogPriority.value = task.priority
                dialogRelatedLesson.value = task.lessons
                checkState.value = task.check
            }
        }
    }

    override fun onBottomDialogEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.OnCancel -> {
                viewModelScope.launch {
                    uiManager.navigateBack()
                }
            }

            DetailsEvent.Delete -> {
                viewModelScope.launch {
                    _task.value?.let { repository.delete(it) }
                    uiManager.navigateBack()
                    uiManager.sendSnackBar(
                        message = "Задача удалена.",
                        type = SnackBarType.SUCCESS,
                        duration = SnackbarDuration.Short
                    )
                }
            }

            DetailsEvent.Edit -> {
                isSheetOpen.value = true
            }

            DetailsEvent.OnConfirm -> {
                viewModelScope.launch {
                    _task.value?.let {
                        repository.insert(
                            it.copy(
                                title = dialogTitle.value,
                                description = dialogDescription.value,
                                dueDate = dialogDueDate.value,
                                priority = dialogPriority.value,
                                lessons = dialogRelatedLesson.value,
                                check = checkState.value
                            )
                        )
                    }
                    uiManager.navigateTo(Screen.TASKS.route)
                    uiManager.sendSnackBar(
                        message = "Задача обновлена.",
                        type = SnackBarType.SUCCESS,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
}