package com.example.universityschedule.presentation.screens.tasks.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.navigation.NavigationManager
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
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val taskId: Int = savedStateHandle.get<Int>("taskId") ?: -1

    private val _task = MutableStateFlow<TaskItem?>(null)
    val task: StateFlow<TaskItem?> = _task

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
                    navigationManager.navigateBack()
                }
            }

            DetailsEvent.Edit -> {

            }
            DetailsEvent.OnCancel -> {
                viewModelScope.launch {
                    navigationManager.navigateBack()
                }
            }

            DetailsEvent.OnConfirm -> {

            }
        }
    }


}