package com.example.universityschedule.domain.repository

import com.example.universityschedule.domain.model.TaskItemEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(item: TaskItemEntity)

    suspend fun delete(item: TaskItemEntity)

    fun getItems(): Flow<List<TaskItemEntity>>
    suspend fun getItemById(id: Int): TaskItemEntity

}