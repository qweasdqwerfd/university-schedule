package com.example.universityschedule.presentation.screens.tasks.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val repository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskId: Int = savedStateHandle.get<Int>("taskId") ?: -1

    private val _task = MutableStateFlow<TaskItem?>(null)
    val task: StateFlow<TaskItem?> = _task

    private val _navigation = Channel<String>()
    val navigation = _navigation.receiveAsFlow()

    init {
        viewModelScope.launch {
            _task.value = repository.getItemById(taskId)
        }
    }


    fun onDialogEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.Delete -> {
                viewModelScope.launch {
                    _task.value?.let { repository.delete(it) }
                    _navigation.send(Screen.TASKS.route)
                }
            }

            DetailsEvent.Edit -> {

            }
            DetailsEvent.OnCancel -> {
                viewModelScope.launch {
                    navigateTo(Screen.TASKS.route)
                }
            }

            DetailsEvent.OnConfirm -> {

            }
        }
    }

    fun navigateTo(screen: String) {
        viewModelScope.launch {
            _navigation.send(screen)
        }
    }


}