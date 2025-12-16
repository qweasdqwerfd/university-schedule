package com.example.universityschedule.data.workmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.universityschedule.R
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

            Log.d("qwe", "GroupsSyncWorker STARTED")
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
            Log.d("work_manager", "GroupsSyncWorker failed: ${e.message}", e)
            Result.retry()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val notification = NotificationCompat.Builder(
            applicationContext,
            "sync_channel"
        )
            .setSmallIcon(R.drawable.ic_sync)
            .setContentTitle("Синхронизация расписания")
            .setContentText("Обновляем данные…")
            .setOngoing(true)
            .build()

        return ForegroundInfo(
            1, // notificationId
            notification
        )
    }
}
