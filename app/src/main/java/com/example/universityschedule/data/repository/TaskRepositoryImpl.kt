package com.example.universityschedule.data.repository

import com.example.universityschedule.data.local.TaskDao
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
): TaskRepository {
    override suspend fun insert(item: TaskItem) {
        return dao.insert(item)
    }

    override suspend fun delete(item: TaskItem) {
        return dao.delete(item)
    }

    override fun getItems(): Flow<List<TaskItem>> {
        return dao.getItems()
    }

    override suspend fun getItemById(id: Int): TaskItem {
        return dao.getItemById(id)
    }
}