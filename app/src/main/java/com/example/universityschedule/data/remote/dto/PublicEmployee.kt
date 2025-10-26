package com.example.universityschedule.data.remote.dto

data class PublicEmployee(
    val id: Int,
    val dekanat_id: Int?,
    val uid: String?,
    val short_name: String,
    val full_name: String,
    val degree: String?,
    val title: String?,
    val departments: Boolean,
    val is_vacancy: Boolean?
)
