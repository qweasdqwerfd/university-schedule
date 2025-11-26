package com.example.universityschedule.domain.repository

import com.example.universityschedule.data.remote.dto.GroupWithPartGroups

interface DictRepository {

    suspend fun getAllGroups(
        pageSize: Int,
        name: String?,
        dekanatId: String?,
        isActive: String?,
        departmentId: String?
    ): List<GroupWithPartGroups>


}
