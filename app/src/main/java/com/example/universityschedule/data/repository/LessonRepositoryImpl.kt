package com.example.universityschedule.data.repository

import com.example.universityschedule.data.local.LessonDao
import com.example.universityschedule.domain.model.LessonItem
import com.example.universityschedule.domain.repository.LessonRepository
import kotlinx.coroutines.flow.Flow

class LessonRepositoryImpl(
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