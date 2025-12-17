package com.example.universityschedule.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityschedule.domain.model.LessonEntity
import java.time.LocalDate

@Dao
interface LessonsDao {

    @Query("""
        SELECT * FROM lessons_table
        WHERE groupId = :groupId
        AND date BETWEEN :start AND :end
    """)
    suspend fun getLessons(
        groupId: Int,
        start: LocalDate,
        end: LocalDate
    ): List<LessonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<LessonEntity>)

    @Query("DELETE FROM lessons_table WHERE groupId != :newGroupId")
    suspend fun clearOtherGroups(newGroupId: Int)

    @Query("DELETE FROM lessons_table")
    suspend fun clearAll()
}
