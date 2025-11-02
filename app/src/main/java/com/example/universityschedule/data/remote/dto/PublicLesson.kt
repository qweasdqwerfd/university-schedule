package com.example.universityschedule.data.remote.dto

import com.example.universityschedule.presentation.screens.calendar.LessonType

data class PublicLesson(
    val id: Int?,
    val subject_name: String?,
    val subject_short_name: String?,
    val start_time: String?,
    val end_time: String?,
    val day: String?,
    val employees: List<PublicEmployee>,
    val part_groups: List<PublicPartGroup>,
    val groups: List<PublicPartGroup>?,
    val rooms: List<PublicRoom>,
    val type: LessonType,
    val type_name: String,
    val type_name_short: String?,
    val is_introductory: Boolean,
    val has_test: Boolean,
    val lesson_numbers: List<String>?,
    val is_dist: Boolean?
)
