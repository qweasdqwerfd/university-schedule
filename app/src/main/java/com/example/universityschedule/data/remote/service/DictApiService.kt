package com.example.universityschedule.data.remote.service

import com.example.universityschedule.data.remote.dto.Department
import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.dto.PublicBuilding
import com.example.universityschedule.data.remote.dto.PublicDepartment
import com.example.universityschedule.data.remote.dto.PublicEmployee
import com.example.universityschedule.data.remote.dto.PublicPartGroup
import com.example.universityschedule.data.remote.dto.PublicRoom
import com.example.universityschedule.data.remote.response.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DictApiService {

    @GET("dict/buildings/")
    suspend fun getBuildings(
        @Query("name") name: String? = null,
        @Query("department_id") department_id: String? = null,
    ): List<PublicBuilding>

    @GET("dict/buildings/{id}/")
    suspend fun getBuildingsById(
        @Path("id") id: Int
    ): PublicBuilding

    @GET("dict/departments/")
    suspend fun getDepartments(
        @Query("type") type: Department? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") page_size: Int? = null,
    ): PaginatedResponse<PublicDepartment>

    @GET("dict/departments/{id}/")
    suspend fun getDepartmentsById(
        @Path("id") id: Int
    ): PublicDepartment

    @GET("dict/employees/")
    suspend fun getEmployees(
        @Query("is_vacancy") is_vacancy: String? = null,
        @Query("department_id") department_id: String? = null,
        @Query("has_lessons_after_date") has_lessons_after_date: String? = null,
        @Query("has_lessons_before_date") has_lessons_before_date: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") page_size: Int? = null,
    ): PaginatedResponse<PublicEmployee>

    @GET("dict/employees/{id}/")
    suspend fun getEmployeesById(
        @Path("id") id: Int
    ): PublicEmployee

    @GET("dict/groups/")
    suspend fun getGroups(
        @Query("name") name: String? = null,
        @Query("dekanat_id") dekanat_id: String? = null,
        @Query("is_active") is_active: String? = null,
        @Query("department_id") department_id: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") page_size: Int? = null,
    ): PaginatedResponse<GroupWithPartGroups>

    @GET("dict/groups/{id}/")
    suspend fun getGroupsById(
        @Path("id") id: Int
    ): GroupWithPartGroups

    @GET("dict/part_groups/")
    suspend fun getPartGroups(
        @Query("name") name: String? = null,
        @Query("department_id") department_id: String? = null,
        @Query("is_active") is_active: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") page_size: Int? = null,
    ): PaginatedResponse<PublicPartGroup>

    @GET("dict/part_groups/{id}/")
    suspend fun getPartGroupsById(
        @Path("id") id: Int
    ): PublicPartGroup

    @GET("dict/rooms/")
    suspend fun getRooms(
        @Query("building") building: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") page_size: Int? = null,
    ): PaginatedResponse<PublicRoom>

    @GET("dict/rooms/{id}/")
    suspend fun getRoomsById(
        @Path("id") id: Int
    ): PublicRoom
}