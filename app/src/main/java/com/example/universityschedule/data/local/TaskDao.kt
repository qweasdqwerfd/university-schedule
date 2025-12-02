package com.example.universityschedule.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityschedule.domain.model.TaskItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TaskItemEntity)
    @Delete
    suspend fun delete(item: TaskItemEntity)
    @Query("SELECT * FROM task_table")
    fun getItems(): Flow<List<TaskItemEntity>>
    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getItemById(id: Int): TaskItemEntity
}