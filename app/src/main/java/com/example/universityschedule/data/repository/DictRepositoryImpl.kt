package com.example.universityschedule.data.repository

import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.domain.repository.DictRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DictRepositoryImpl @Inject constructor(
    private val api: DictApiService
) : DictRepository {

    override suspend fun getAllGroups(
        pageSize: Int,
        name: String?,
        dekanatId: String?,
        isActive: String?,
        departmentId: String?
    ): List<GroupWithPartGroups> = withContext(Dispatchers.IO) {

        val all = mutableListOf<GroupWithPartGroups>()
        var page = 1

        try {
            while (true) {
                val resp = api.getGroups(
                    name = name,
                    dekanat_id = dekanatId,
                    is_active = isActive,
                    department_id = departmentId,
                    page = page,
                    page_size = pageSize
                )

                all += resp.results

                if (resp.next == null) break

                page++
            }
        } catch (e: Exception) {
            throw e
        }

        all.toList()
    }
}
