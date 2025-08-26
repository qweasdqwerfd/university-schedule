package com.example.universityschedule.data.view_models

import android.util.Log
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
    private val repository: TaskRepository,

    ) : ViewModel(), TaskDialogController {

    init {
        Log.d("TaskVM", "ViewModel created: ${this.hashCode()}")
    }


    val itemsList = repository.getItems()

    override var dialogTitle = mutableStateOf("")
    override var dialogDescription = mutableStateOf("")
    override var dialogDueDate = mutableStateOf("")
    override var dialogPriority = mutableStateOf(Priority.MEDIUM)
    override var dialogRelatedLesson = mutableStateOf(Lesson.NONE)


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

                    Log.d("qwe", "Saving: $task")

                    repository.insert(task)

                    Log.d(
                        "qwe",
                        "title=${dialogTitle.value}," +
                                " desc=${dialogDescription.value}," +
                                " date=${dialogDueDate.value} +" +
                                "lesson: ${dialogRelatedLesson.value} +" +
                                "priority: ${dialogPriority.value}"
                    )


                }
            }


            DialogEvent.OnCancel -> {

            }
        }
    }
}