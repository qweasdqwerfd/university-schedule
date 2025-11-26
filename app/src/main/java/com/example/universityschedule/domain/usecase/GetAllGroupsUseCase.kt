package com.example.universityschedule.domain.usecase

import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.domain.repository.DictRepository
import javax.inject.Inject

class GetAllGroupsUseCase @Inject constructor(
    private val repository: DictRepository
) {
    suspend operator fun invoke(
        pageSize: Int,
        name: String?,
        dekanatId: String?,
        isActive: String?,
        departmentId: String?,
    ): List<GroupWithPartGroups> = repository.getAllGroups(
        pageSize,
        name,
        dekanatId,
        isActive,
        departmentId
    )
}