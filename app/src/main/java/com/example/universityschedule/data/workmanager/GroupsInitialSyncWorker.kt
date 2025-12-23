package com.example.universityschedule.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.universityschedule.domain.repository.GroupsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GroupsInitialSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    repo: GroupsRepository
) : BaseGroupsSyncWorker(context, params, repo) {

    override suspend fun doWork(): Result {
        return try {
            if (repo.isInitialSyncDone()) {
                Log.d("qwe", "Initial sync already done — skip")
                return Result.success()
            }

            Log.d("qwe", "GroupsInitialSyncWorker STARTED")

            syncGroups()

            repo.markInitialSyncDone()

            Result.success()
        } catch (e: Exception) {
            Log.e("work_manager", "Initial sync failed", e)
            Result.retry()
        }
    }
}
