package com.example.universityschedule.presentation.screens.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.data.remote.dto.PublicPartGroup
import com.example.universityschedule.data.remote.dto.PublicStaticLesson
import com.example.universityschedule.data.remote.response.PaginatedResponse
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.domain.usecases.FetchWeekUseCase
import com.example.universityschedule.domain.usecases.GetLessonsUseCase
import com.example.universityschedule.presentation.common.dialog_controller.DialogController
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val dialogController: DialogController,
    private val fetchWeekIfNeeded: FetchWeekUseCase,
    private val prefs: UserPrefsRepository,
    private val lessonsRepository: LessonsRepository,
) : ViewModel(), DialogController by dialogController {

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    private val _lessonsByDate =
        MutableStateFlow<Map<LocalDate, List<Lesson>>>(emptyMap())
    val lessonsByDate = _lessonsByDate.asStateFlow()

    private val fetchingWeeks = mutableSetOf<LocalDate>()
    private val _isLoadingCurrentWeek = MutableStateFlow(false)
    val isLoadingCurrentWeek = _isLoadingCurrentWeek.asStateFlow()

    private var currentGroupId: Int? = null
    private var currentWeekStart: LocalDate? = null


    init {
        viewModelScope.launch {
            prefs.selectedGroupId
                .filterNotNull()
                .distinctUntilChanged()
                .collect { newGroupId ->
                    lessonsRepository.onGroupChanged(newGroupId)
                }
        }
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date

        val startOfWeek = date.with(DayOfWeek.MONDAY)
        if (currentWeekStart == startOfWeek) return

        currentWeekStart = startOfWeek
        loadWeek(startOfWeek)
    }

    private fun loadWeek(startOfWeek: LocalDate) {
        viewModelScope.launch {
            fetchWeekIfNeeded(
                startOfWeek,
                markAsCurrent = true,
                lessonsByDate = _lessonsByDate,
                fetchingWeeks = fetchingWeeks,
                isLoadingCurrentWeek = _isLoadingCurrentWeek
            )
        }
    }
}

//    private val _namesLessons = mutableStateOf<Set<String>>(emptySet())
//    val namesLessons: MutableState<Set<String>> get() = _namesLessons
//    fun addNamesLessonsForTasks(startOfWeek: LocalDate, endOfWeek: LocalDate) {
//        viewModelScope.launch {
//
//            val lessonsOfWeek = getLessons(startOfWeek, endOfWeek)
//            val namesLessons: Set<String?> =
//                lessonsOfWeek.map { it.subjectName }.toSet()
//            setNamesLessons(namesLessons)
//
//        }
//    }
//
//    fun setNamesLessons(names: Set<String?>) {
//        _namesLessons.value = names.filterNotNull().toSet()
//        Log.d("qwe", _namesLessons.value.toSet().toString() )
//    }


