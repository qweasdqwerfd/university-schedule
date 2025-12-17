package com.example.universityschedule.data.remote.service

import com.example.universityschedule.data.remote.response.LessonsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface LessonsApiService {

    @GET("lessons/by_employee/")
    suspend fun getLessonsByEmployee(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("semester") semester: Int? = null,
        @Query("id") id: Int? = null,
        @Query("dekanat_id") dekanatId: Int? = null,
        @Query("uid") uid: String? = null
    ): LessonsResponse

    @GET("lessons/by_group/")
    suspend fun getLessonsByGroup(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("semester") semester: Int? = null,
        @Query("id") id: Int
    ): LessonsResponse

    @GET("lessons/by_part_group/")
    suspend fun getLessonsByPartGroup(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("semester") semester: Int? = null,
        @Query("id") id: Int
    ): LessonsResponse

    @GET("lessons/by_room/")
    suspend fun getLessonsByRoom(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("semester") semester: Int? = null,
        @Query("id") id: Int
    ): LessonsResponse



}