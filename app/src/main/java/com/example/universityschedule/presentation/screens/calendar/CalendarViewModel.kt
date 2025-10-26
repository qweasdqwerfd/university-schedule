package com.example.universityschedule.presentation.screens.calendar

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.remote.api.RetrofitClient
import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.data.remote.dto.PublicPartGroup
import com.example.universityschedule.data.remote.response.PaginatedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(): ViewModel() {

    private val lessonsApi = RetrofitClient.lessonsApiService
    private val dictApi = RetrofitClient.dictApiService

    private val _groups = mutableStateOf<PaginatedResponse<PublicPartGroup>>(
        PaginatedResponse(
            count = 0,
            next = null,
            previous = null,
            results = emptyList()
        )
    )

    val groups: MutableState<PaginatedResponse<PublicPartGroup>> get() = _groups

    fun findTargetGroup() {
        viewModelScope.launch {
            var currentPage = 1
            var foundGroup: PublicPartGroup? = null

            while (foundGroup == null) {
                try {
                    val response = dictApi.getPartGroups(page = currentPage)
                    Log.d("qwe", "Page $currentPage groups: ${response.results.map { it.name }}")

                    foundGroup = response.results.find { it.name == "ПВ-242(1)" }

                    if (foundGroup != null) {
                        Log.d("qwe", "FOUND on page $currentPage: $foundGroup")
                        _groups.value = response // или сохраните найденную группу
                        break
                    }

                    // Если нет следующей страницы - выходим
                    if (response.next == null) {
                        Log.d("qwe", "Group 'ПВ-242' not found in all pages")
                        break
                    }

                    currentPage++
                } catch (e: Exception) {
                    Log.e("qwe", "Error loading page $currentPage", e)
                    break
                }
            }
        }
    }



}