package com.example.universityschedule.data.workmanager

import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import com.example.universityschedule.App
import com.example.universityschedule.R

fun CoroutineWorker.createForegroundInfo(): ForegroundInfo {
    val notification = NotificationCompat.Builder(
        applicationContext,
        App.SYNC_CHANNEL_ID
    )
        .setSmallIcon(R.drawable.ic_sync)
        .setContentTitle("Синхронизация расписания")
        .setContentText("Обновляем данные…")
        .setOngoing(true)
        .build()

    return ForegroundInfo(
        1,
        notification
    )
}
