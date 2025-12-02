package com.example.universityschedule.domain.repository

import com.example.universityschedule.domain.model.GroupEntity
import kotlinx.coroutines.flow.Flow

interface GroupsRepository {
    fun observeCachedGroups(): Flow<List<GroupEntity>>
    suspend fun fetchAndCacheAllGroups(
        pageSize: Int,
        name: String?,
        dekanatId: String?,
        isActive: String?,
        departmentId: String?
    )
    suspend fun clearCachedGroups()

}