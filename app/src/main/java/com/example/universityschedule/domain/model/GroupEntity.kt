package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.data.remote.dto.DepartmentShort

@Entity(tableName = "groups_table")
data class GroupEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val course: Int?,
    val institute: String
)