package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "loaded_weeks")
data class LoadedWeekEntity(
    @PrimaryKey val weekStart: LocalDate,
    val groupId: Int,
    val loadedAt: Long = System.currentTimeMillis()
)
