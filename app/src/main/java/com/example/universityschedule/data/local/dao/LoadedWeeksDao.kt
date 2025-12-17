package com.example.universityschedule.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityschedule.domain.model.LoadedWeekEntity
import java.time.LocalDate

@Dao
interface LoadedWeeksDao {

    @Query("DELETE FROM loaded_weeks WHERE groupId != :newGroupId")
    suspend fun clearOtherGroups(newGroupId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markWeekLoaded(entity: LoadedWeekEntity)

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM loaded_weeks
            WHERE groupId = :groupId AND weekStart = :weekStart
        )
    """)
    suspend fun isWeekLoaded(groupId: Int, weekStart: LocalDate): Boolean
}
