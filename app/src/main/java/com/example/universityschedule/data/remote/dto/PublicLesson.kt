package com.example.universityschedule.data.remote.dto

import com.example.universityschedule.presentation.screens.calendar.LessonType

data class PublicLesson(
    val id: Int?,
    val subject_name: String?,
    val subject_short_name: String?,
    val start_time: String?,
    val end_time: String?,
    val day: String?,
    val employees: PublicEmployee,
    val part_groups: PublicPartGroup,
    val groups: String?,
    val rooms: PublicRoom,
    val type: LessonType,
    val type_name: String,
    val type_name_short: String?,
    val is_introductory: Boolean,
    val has_test: Boolean,
    val lesson_numbers: String?,
    val is_dist: Boolean?
)
