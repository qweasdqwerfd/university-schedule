package com.example.universityschedule.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.universityschedule.data.models.TaskModel

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> get() = _tasks

    fun addTask(task: TaskModel) {
        _tasks.add(task)
    }
}