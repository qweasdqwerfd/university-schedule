package com.example.universityschedule.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.local.dao.GroupDao
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.dto.PublicPartGroup
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.repository.GroupsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GroupsRepositoryImpl @Inject constructor(
    private val api: DictApiService,
    private val dao: GroupDao,
    private val prefs: UserPrefsRepository
) : GroupsRepository {

    override fun observeSelectedGroup(id: Int): Flow<GroupEntity> =
        dao.observeById(id)

    override suspend fun refreshSelectedGroup(id: Int) {

        prefs.selectedGroupId
            .filterNotNull()
            .distinctUntilChanged()
            .onEach { groupId ->
                val remote = api.getPartGroupsById(groupId)
                dao.insert(
                    GroupEntity(
                        id = remote.id!!.toLong(),
                        name = remote.name.orEmpty(),
                        institute = remote.institute.short_name.orEmpty(),
                        course = remote.group
                    )
                )
            }
    }



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

            // flatMap: для каждой записи берём все part_groups, если их нет — fallback на саму запись
            val entities = groupsList.flatMap { gw ->
                val partGroups = gw.part_groups
                if (!partGroups.isNullOrEmpty()) {
                    partGroups.map { pg ->
                        GroupEntity(
                            id = pg.id!!.toLong(),
                            name = pg.name ?: gw.name,
                            course = gw.course,
                            institute = pg.institute.short_name.toString()
                        )
                    }
                } else {
                    // если part_groups нет, используем сам объект gw
                    listOf(
                        GroupEntity(
                            id = gw.id!!.toLong(),
                            name = gw.name,
                            course = gw.course,
                            institute = gw.institute.short_name.toString()
                        )
                    )
                }
            }

            Log.d("GroupsRepository", "Fetched total entities = ${entities.size}")

            dao.insertAll(entities)
        } catch (e: Exception) {
            Log.w("GroupsRepository", "fetchAndCache failed: ${e.message}", e)
        }
    }

    override suspend fun clearCachedGroups() {
        TODO("Not yet implemented")
    }



}