package com.example.universityschedule.data.remote.dto

data class PublicRoom(
    val id: Int?,
    val building: Int,
    val building_name: String,
    val name: String,
    val full_name: String?
)