package com.example.universityschedule.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityschedule.domain.model.LessonItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LessonItem)
    @Delete
    suspend fun delete(item: LessonItem)
    @Query("SELECT * FROM lesson_table")
    fun getItems(): Flow<List<LessonItem>>
}