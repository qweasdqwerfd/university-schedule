package com.example.universityschedule.data.repository

import com.example.universityschedule.data.local.dao.TaskDao
import com.example.universityschedule.domain.model.TaskItemEntity
import com.example.universityschedule.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
): TaskRepository {
    override suspend fun insert(item: TaskItemEntity) {
        return dao.insert(item)
    }

    override suspend fun delete(item: TaskItemEntity) {
        return dao.delete(item)
    }

    override fun getItems(): Flow<List<TaskItemEntity>> {
        return dao.getItems()
    }

    override suspend fun getItemById(id: Int): TaskItemEntity {
        return dao.getItemById(id)
    }
}