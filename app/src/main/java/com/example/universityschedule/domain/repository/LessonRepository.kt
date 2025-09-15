package com.example.universityschedule.domain.repository

import com.example.universityschedule.domain.model.LessonItem
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    suspend fun insert(item: LessonItem)

    suspend fun delete(item: LessonItem)

    fun getItems(): Flow<List<LessonItem>>
}