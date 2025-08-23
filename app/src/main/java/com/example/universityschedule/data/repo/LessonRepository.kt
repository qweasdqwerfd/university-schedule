package com.example.universityschedule.data.repo

import com.example.universityschedule.data.entities.LessonItem
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    suspend fun insert(item: LessonItem)

    suspend fun delete(item: LessonItem)

    fun getItems(): Flow<List<LessonItem>>
}