package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups_table")
data class GroupEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val course: Int?,
    val studentCount: Int?
)