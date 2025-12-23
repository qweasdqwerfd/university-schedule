package com.example.universityschedule.data.repository

import android.util.Log
import com.example.universityschedule.data.local.dao.GroupDao
import com.example.universityschedule.data.local.datastore.sync.SyncPrefsDataStore
import com.example.universityschedule.data.local.datastore.user.UserPrefsDataStore
import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.repository.GroupsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroupsRepositoryImpl @Inject constructor(
    private val api: DictApiService,
    private val dao: GroupDao,
    private val prefs: UserPrefsDataStore,
    private val syncPrefs: SyncPrefsDataStore

) : GroupsRepository {

    override suspend fun isInitialSyncDone(): Boolean =
        syncPrefs.isGroupsInitialSyncDone()

    override suspend fun markInitialSyncDone() =
        syncPrefs.markGroupsInitialSyncDone()

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
    }

    override fun observeSelectedGroup(id: Int): Flow<GroupEntity> =
        dao.observeById(id)

    override suspend fun clearCachedGroups() {
        TODO("Not yet implemented")
    }



}