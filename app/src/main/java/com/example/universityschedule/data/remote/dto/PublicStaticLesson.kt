package com.example.universityschedule.data.remote.dto

data class PublicStaticLesson(
    val id: Int?,
    val subject_name: String?,
    val subject_short_name: String?,
    val lesson_number: Int,
    val day_number: Int,
    val start_time: String,
    val end_time: String,
    val employees: List<PublicEmployee>,
    val part_groups: List<PublicPartGroup>,
    val groups: List<PublicPartGroup>?,
    val rooms: List<PublicRoom>,
    val type: String,
    val type_name: String,
    val type_name_short: String?,
    val dates: List<String>?,
    val week: Int,
    val is_introductory: Boolean,
    val is_dist: Boolean?
)
