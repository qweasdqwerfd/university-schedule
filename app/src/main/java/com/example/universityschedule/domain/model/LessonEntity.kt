package com.example.universityschedule.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universityschedule.data.remote.dto.PublicEmployee
import com.example.universityschedule.data.remote.dto.PublicRoom
import com.example.universityschedule.presentation.screens.calendar.components.enums.LessonType
import java.time.LocalDate

@Entity(
    tableName = "lessons_table",
    primaryKeys = ["lessonId", "date", "groupId"]
)
data class LessonEntity(
    val lessonId: Int,
    val groupId: Int,
    val date: LocalDate,

    val subjectName: String,
    val startTime: String,
    val endTime: String,
    val type: LessonType,

    val locationJson: String,
    val teacherJson: String
)