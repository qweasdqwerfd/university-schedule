package com.example.universityschedule

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.universityschedule.data.workmanager.GroupsDailySyncWorker
import com.example.universityschedule.data.workmanager.GroupsInitialSyncWorker
import dagger.hilt.android.HiltAndroidApp
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        createSyncChannel()
        scheduleGroupsSync()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()

    private fun scheduleGroupsSync() {
        val workManager = WorkManager.getInstance(this)

        /* ========= 1️⃣ FIRST LAUNCH ========= */
        val initialRequest =
            OneTimeWorkRequestBuilder<GroupsInitialSyncWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

        workManager.enqueueUniqueWork(
            "groups_initial_sync",
            ExistingWorkPolicy.KEEP,
            initialRequest
        )

        /* ========= 2️⃣ DAILY NIGHT ========= */
        val periodicRequest =
            PeriodicWorkRequestBuilder<GroupsDailySyncWorker>(
                1, TimeUnit.DAYS
            )
                .setInitialDelay(
                    calculateNightDelay(),
                    TimeUnit.MILLISECONDS
                )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .build()

        workManager.enqueueUniquePeriodicWork(
            "groups_daily_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }

    private fun calculateNightDelay(): Long {
        val now = LocalDateTime.now()
        val nextNight = now.toLocalDate()
            .atTime(3, 0)
            .let { if (it.isBefore(now)) it.plusDays(1) else it }

        return Duration.between(now, nextNight).toMillis()
    }

    private fun createSyncChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                SYNC_CHANNEL_ID,
                "Синхронизация",
                NotificationManager.IMPORTANCE_LOW
            )

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    companion object {
        const val SYNC_CHANNEL_ID = "sync_channel"
    }
}
