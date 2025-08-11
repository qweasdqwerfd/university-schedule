package com.example.universityschedule.data.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.universityschedule.data.App
import com.example.universityschedule.data.MainDB
import com.example.universityschedule.data.entities.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val database: MainDB
) : ViewModel() {


    val itemsList = database.taskDao.getItems()
    var tempEntity: TaskEntity? = null


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

    var isSaved by mutableStateOf(false)
        private set




    fun onPriorityChange(newValue: String) { selectedPriority = newValue }
    fun onLessonChange(newValue: String) { selectedLessons = newValue }
    fun onTitleChange(newValue: String) { titleText = newValue }
    fun onDescriptionChange(newValue: String) { descriptionText = newValue }
    fun onDueDateChange(newValue: String) { dueDate = newValue }


    fun insertItem() = viewModelScope.launch {
        isSaved = false // СБРОС перед любыми операциями

        val newEntity = tempEntity?.copy(
            priority = selectedPriority,
            lessons = selectedLessons,
            title = titleText,
            description = descriptionText,
            dueDate = dueDate
        ) ?: TaskEntity(
            priority = selectedPriority,
            lessons = selectedLessons,
            title = titleText,
            description = descriptionText,
            dueDate = dueDate
        )

        Log.d("TAG", newEntity.toString())

        database.taskDao.insertItem(newEntity)
        isSaved = true

        // очистка полей
        tempEntity = null
        selectedPriority = ""
        selectedLessons = ""
        titleText = ""
        descriptionText = ""
        dueDate = ""
    }



    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")

            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).dataBase
                return TaskViewModel(database) as T
            }
        }
    }

}