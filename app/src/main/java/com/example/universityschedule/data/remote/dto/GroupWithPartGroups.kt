package com.example.universityschedule.data.remote.dto

data class GroupWithPartGroups(
    val id: Int?,
    val dekanat_id: Int?,
    val name: String,
    val education_form: Int?,
    val course: Int?,
    val department: Int,
    val university: DepartmentShort,
    val institute: DepartmentShort,
    val part_groups: List<PublicPartGroup>
)