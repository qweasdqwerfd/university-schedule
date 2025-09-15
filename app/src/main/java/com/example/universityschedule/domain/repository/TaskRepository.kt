package com.example.universityschedule.domain.repository

import com.example.universityschedule.domain.model.TaskItem
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(item: TaskItem)

    suspend fun delete(item: TaskItem)

    fun getItems(): Flow<List<TaskItem>>
    suspend fun getItemById(id: Int): TaskItem

}