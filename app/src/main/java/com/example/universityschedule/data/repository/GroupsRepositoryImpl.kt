package com.example.universityschedule.data.repository

import android.util.Log
import com.example.universityschedule.data.local.GroupDao
import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.repository.GroupsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class GroupsRepositoryImpl @Inject constructor(
    private val api: DictApiService,
    private val dao: GroupDao
) : GroupsRepository {

    override fun observeCachedGroups(): Flow<List<GroupEntity>> = dao.observeAll()

    override suspend fun fetchAndCacheAllGroups(
        pageSize: Int,
        name: String?,
        dekanatId: String?,
        isActive: String?,
        departmentId: String?
    ) {

        val groupsList = mutableListOf<GroupWithPartGroups>()
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

                groupsList += resp.results

                if (resp.next == null) break

                page++
            }


            groupsList.toList().asFlow()


            // map to entity
            val entities = groupsList.map { gw ->
                GroupEntity(
                    id = gw.id!!.toLong(),
                    name = gw.name,
                    course = gw.course,
                    studentCount = gw.part_groups.firstOrNull()?.student_count
                )
            }

            // atomically replace cache (опционально clearAll)
            dao.insertAll(entities)
        } catch (e: Exception) {
            // логируем — не бросаем, UI не должен крашиться
            Log.w("GroupsRepository", "fetchAndCache failed: ${e.message}")
        }
    }

    override suspend fun clearCachedGroups() {
        TODO("Not yet implemented")
    }
}