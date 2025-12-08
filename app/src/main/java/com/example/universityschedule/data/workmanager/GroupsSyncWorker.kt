package com.example.universityschedule.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.universityschedule.domain.repository.GroupsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GroupsSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repo: GroupsRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            // Огромным page_size вытащим всё за 1–3 запроса
            repo.fetchAndCacheAllGroups(
                pageSize = 400,
                name = null,
                dekanatId = null,
                isActive = null,
                departmentId = null
            )

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}
