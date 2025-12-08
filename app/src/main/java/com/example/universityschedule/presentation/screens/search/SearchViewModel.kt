package com.example.universityschedule.presentation.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.remote.dto.GroupWithPartGroups
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.domain.repository.GroupsRepository
import com.example.universityschedule.domain.usecases.GetAllGroupsUseCase
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.common.dialog_controller.DialogController
import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val uiManager: UIManager,
    private val dialogController: DialogController,
//    private val getAllGroups: GetAllGroupsUseCase
    private val getAllGroups: GroupsRepository
) : ViewModel(), DialogController by dialogController {

    private val _groupsList = mutableStateOf<Flow<List<GroupEntity>>>(emptyFlow())
    val groupsList: MutableState<Flow<List<GroupEntity>>> get() = _groupsList


    override fun onDialogEvent(event: DialogEvent) {

        when (event) {
            is DialogEvent.OnButtonClick -> {
                viewModelScope.launch {
                    uiManager.navigateTo(Screen.SEARCH_SCHEDULE.route)
                }
            }

            is DialogEvent.OnCancel -> {
                viewModelScope.launch {
                    uiManager.navigateBack()
                }
            }

            else -> {}
        }
    }
    fun getGroups() {
        viewModelScope.launch {
            _groupsList.value = getAllGroups.observeCachedGroups()
        }
    }


}