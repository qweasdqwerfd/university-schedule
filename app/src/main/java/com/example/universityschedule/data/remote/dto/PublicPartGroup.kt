package com.example.universityschedule.data.remote.dto

data class PublicPartGroup(
    val id: Int?,
    val name: String?,
    val student_count: Int?,
    val group: Int,
    val university: DepartmentShort,
    val institute: DepartmentShort
)