package com.example.universityschedule.presentation.screens.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.remote.dto.PublicPartGroup
import com.example.universityschedule.data.remote.dto.PublicStaticLesson
import com.example.universityschedule.data.remote.response.PaginatedResponse
import com.example.universityschedule.domain.model.Lesson
import com.example.universityschedule.domain.usecases.FetchWeekUseCase
import com.example.universityschedule.domain.usecases.GetLessonsUseCase
import com.example.universityschedule.presentation.common.dialog_controller.DialogController
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val dialogController: DialogController,
    private val fetchWeekIfNeeded: FetchWeekUseCase,
    private val uiManager: UIManager,
    private val getLessonsUseCase: GetLessonsUseCase
) : ViewModel(), DialogController by dialogController {
    private val _lessons = mutableStateOf<List<Lesson>?>(
        null
    )
    val lessons: MutableState<List<Lesson>?> get() = _lessons


    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    private val _namesLessons = mutableStateOf<Set<String>>(emptySet())
    val namesLessons: MutableState<Set<String>> get() = _namesLessons


    // кэш
    private val _lessonsByDate = MutableStateFlow<Map<LocalDate, List<Lesson>>>(emptyMap())
    val lessonsByDate: StateFlow<Map<LocalDate, List<Lesson>>> = _lessonsByDate.asStateFlow()

    private val fetchingWeeks = mutableSetOf<LocalDate>()

    private val _isLoadingCurrentWeek = MutableStateFlow(false)
    val isLoadingCurrentWeek: StateFlow<Boolean> = _isLoadingCurrentWeek.asStateFlow()


    fun onPageChanged(pageDate: LocalDate) {
        val startOfWeek = pageDate.with(DayOfWeek.MONDAY)

//        val endOfWeek = pageDate.with(DayOfWeek.SUNDAY)
//        addNamesLessonsForTasks(startOfWeek, endOfWeek)

        viewModelScope.launch {

            fetchWeekIfNeeded(
                startOfWeek, markAsCurrent = true,
                lessonsByDate = _lessonsByDate,
                fetchingWeeks = fetchingWeeks,
                isLoadingCurrentWeek = _isLoadingCurrentWeek
            )

            delay(700)
            fetchWeekIfNeeded(
                startOfWeek.minusWeeks(1), markAsCurrent = false,
                lessonsByDate = _lessonsByDate,
                fetchingWeeks = fetchingWeeks,
                isLoadingCurrentWeek = _isLoadingCurrentWeek
            )
            fetchWeekIfNeeded(
                startOfWeek.plusWeeks(1), markAsCurrent = false,
                lessonsByDate = _lessonsByDate,
                fetchingWeeks = fetchingWeeks,
                isLoadingCurrentWeek = _isLoadingCurrentWeek
            )
        }
    }


    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }



}


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


