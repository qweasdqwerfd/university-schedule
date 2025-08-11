package com.example.universityschedule.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universityschedule.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteItem(taskEntity: TaskEntity)

    @Query("SELECT * FROM task_table")
    fun getItems(): Flow<List<TaskEntity>>

}