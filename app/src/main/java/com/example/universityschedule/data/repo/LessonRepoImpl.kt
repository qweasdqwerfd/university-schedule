package com.example.universityschedule.data.repo

import com.example.universityschedule.data.dao.LessonDao
import com.example.universityschedule.data.entities.LessonItem
import kotlinx.coroutines.flow.Flow

class LessonRepoImpl(
    private val dao: LessonDao
): LessonRepository {
    override suspend fun insert(item: LessonItem) {
        return dao.insert(item)
    }

    override suspend fun delete(item: LessonItem) {
        return dao.delete(item)
    }

    override fun getItems(): Flow<List<LessonItem>> {
        return dao.getItems()
    }
}