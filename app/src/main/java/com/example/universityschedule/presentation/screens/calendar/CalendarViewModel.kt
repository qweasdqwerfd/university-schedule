package com.example.universityschedule.presentation.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.repository.GroupsRepository
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.domain.usecases.FetchWeekUseCase
import com.example.universityschedule.presentation.common.dialog_controller.DialogController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
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
    private val groupsRepository: GroupsRepository
) : ViewModel(), DialogController by dialogController {

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    private val _lessonsByDate =
        MutableStateFlow<Map<LocalDate, List<Lesson>>>(emptyMap())
    val lessonsByDate = _lessonsByDate.asStateFlow()

    private val fetchingWeeks = mutableSetOf<LocalDate>()
    private val _isLoadingCurrentWeek = MutableStateFlow(false)
    val isLoadingCurrentWeek = _isLoadingCurrentWeek.asStateFlow()

    private var currentWeekStart: LocalDate? = null


    init {
        viewModelScope.launch {
            prefs.selectedGroupId
                .filterNotNull()
                .distinctUntilChanged()
                .collect { newGroupId ->

                    // 1️⃣ чистим кэш в БД
                    lessonsRepository.onGroupChanged(newGroupId)

                    // 2️⃣ чистим in-memory
                    _lessonsByDate.value = emptyMap()
                    fetchingWeeks.clear()

                    // 3️⃣ сбрасываем маркер недели
                    currentWeekStart = null

                    // 4️⃣ ПРИНУДИТЕЛЬНО грузим текущую неделю
                    val date = _selectedDate.value
                    val startOfWeek = date.with(DayOfWeek.MONDAY)
                    loadWeek(startOfWeek)
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


