package com.example.universityschedule.presentation.screens.lessons

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.domain.repository.LessonRepository
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val repository: LessonRepository,
    private val uiManager: UIManager,
) : ViewModel(), LessonDialogController {

    override var dialogTitle = mutableStateOf("")
    override var dialogProfessor = mutableStateOf("")
    override var dialogLocation = mutableStateOf("")
    override var dialogStartTime = mutableStateOf("")
    override var dialogEndTime = mutableStateOf("")
    @RequiresApi(Build.VERSION_CODES.O)
    override var dialogDayOfWeek = mutableStateOf(LocalDate.now())
    override var dialogColor = mutableStateOf("")

    override fun onDialogEvent(event: DialogEvent) {

        when (event) {
            is DialogEvent.OnFABClick -> {
                viewModelScope.launch {
//                    uiManager.navigateTo()
                }
            }

            is DialogEvent.OnConfirm -> {

            }

            is DialogEvent.OnCancel -> {

            }

            is DialogEvent.OnItemClick -> {

            }

            else -> {}
        }
    }


}