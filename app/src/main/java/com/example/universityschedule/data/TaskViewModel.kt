package com.example.universityschedule.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.universityschedule.data.models.TaskModel

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> get() = _tasks

    var selectedPriority by mutableStateOf("Medium")
        private set

    var selectedLessons by mutableStateOf("None")
        private set

    var titleText by mutableStateOf("")
        private set

    var descriptionText by mutableStateOf("")
        private set

    var dueDate by mutableStateOf("")
        private set


    fun addTask(task: TaskModel) {
        _tasks.add(task)
    }

    fun onPriorityChange(newValue: String) { selectedPriority = newValue }
    fun onLessonChange(newValue: String) { selectedLessons = newValue }
    fun onTitleChange(newValue: String) { titleText = newValue }
    fun onDescriptionChange(newValue: String) { descriptionText = newValue }
    fun onDueDateChange(newValue: String) { dueDate = newValue }

    fun toTaskModel(): TaskModel {
        return TaskModel(
            title = titleText,
            description = descriptionText,
            dueDate = dueDate,
            priority = selectedPriority,
            lesson = selectedLessons
        )
    }

}