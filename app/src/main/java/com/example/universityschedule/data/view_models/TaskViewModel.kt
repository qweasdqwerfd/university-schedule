package com.example.universityschedule.data.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
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
    private val repository: TaskRepository,

) : ViewModel(), TaskDialogController {

    val itemsList = repository.getItems()

    override var dialogTitle = mutableStateOf("")
        private set
    override var dialogDescription = mutableStateOf("")
        private set
    override var dialogDueDate = mutableStateOf("")
        private set
    override var dialogPriority = mutableStateOf(Priority.MEDIUM)
        private set
    override var dialogRelatedLesson = mutableStateOf(Lesson.NONE)
        private set
    override var openDialog = mutableStateOf(false)
        private set

    private var currentTask: TaskItem? = null   // хранит редактируемый таск

    // приватная функция для инициализации диалога
    private fun prepareTask(item: TaskItem?) {
        currentTask = item
        if (item == null) {
            dialogTitle.value = ""
            dialogDescription.value = ""
            dialogDueDate.value = ""
            dialogPriority.value = Priority.MEDIUM
            dialogRelatedLesson.value = Lesson.NONE
        } else {
            dialogTitle.value = item.title
            dialogDescription.value = item.description
            dialogDueDate.value = item.dueDate
            dialogPriority.value = item.priority
            dialogRelatedLesson.value = item.lessons
        }
        openDialog.value = true
    }

    // публичный метод для открытия диалога (можно передавать null для нового таска)
    fun openDialogForTask(item: TaskItem? = null) {
        prepareTask(item)
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            DialogEvent.OnConfirm -> {
                viewModelScope.launch {
                    repository.insert(
                        TaskItem(
                            id = currentTask?.id ?: 0, // если null → новый
                            title = dialogTitle.value,
                            description = dialogDescription.value,
                            dueDate = dialogDueDate.value,
                            priority = dialogPriority.value,
                            lessons = dialogRelatedLesson.value
                        )
                    )
                }
                openDialog.value = false
            }

            DialogEvent.OnCancel -> {
                openDialog.value = false
            }
        }
    }
}