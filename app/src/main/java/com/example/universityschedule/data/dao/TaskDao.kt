package com.example.universityschedule.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityschedule.data.entities.TaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TaskItem)
    @Delete
    suspend fun delete(item: TaskItem)
    @Query("SELECT * FROM task_table")
    fun getItems(): Flow<List<TaskItem>>
    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getItemById(id: Int): TaskItem
}