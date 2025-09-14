package com.example.universityschedule.data.repo

import com.example.universityschedule.data.dao.TaskDao
import com.example.universityschedule.data.entities.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskRepoImpl(
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