package com.example.universityschedule.data.remote.dto

import com.example.universityschedule.presentation.screens.calendar.LessonType

data class PublicStaticLesson(
    val id: Int?,
    val subject_name: String?,
    val subject_short_name: String?,
    val lesson_number: Int,
    val day_number: Int,
    val start_time: String,
    val end_time: String,
    val employees: PublicEmployee,
    val part_groups: PublicPartGroup,
    val groups: String?,
    val rooms: PublicRoom,
    val type: LessonType,
    val type_name: String,
    val type_name_short: String?,
    val dates: String?,
    val week: Int,
    val is_introductory: Boolean,
    val is_dist: Boolean?
)
