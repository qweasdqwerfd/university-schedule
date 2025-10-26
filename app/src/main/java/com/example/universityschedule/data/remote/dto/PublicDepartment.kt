package com.example.universityschedule.data.remote.dto

data class PublicDepartment(
    val name: String?,
    val short_name: String,
    val parent: Int,
    val type: Department
)
