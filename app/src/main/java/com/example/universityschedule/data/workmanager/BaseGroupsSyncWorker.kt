package com.example.universityschedule.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.universityschedule.domain.repository.GroupsRepository

abstract class BaseGroupsSyncWorker(
    context: Context,
    params: WorkerParameters,
    protected val repo: GroupsRepository
) : CoroutineWorker(context, params) {

    protected suspend fun syncGroups() {
        repo.fetchAndCacheAllGroups(
            pageSize = 400,
            name = null,
            dekanatId = null,
            isActive = null,
            departmentId = null
        )
    }

    override suspend fun getForegroundInfo(): ForegroundInfo =
        createForegroundInfo()
}
