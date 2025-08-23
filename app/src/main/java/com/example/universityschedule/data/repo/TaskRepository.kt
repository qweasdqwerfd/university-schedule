package com.example.universityschedule.data.repo

import com.example.universityschedule.data.entities.TaskItem
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(item: TaskItem)

    suspend fun delete(item: TaskItem)

    fun getItems(): Flow<List<TaskItem>>
}