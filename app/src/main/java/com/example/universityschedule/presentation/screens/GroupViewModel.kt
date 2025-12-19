package com.example.universityschedule.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universityschedule.data.local.dao.GroupDao
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.domain.model.GroupEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val api: DictApiService,
    private val dao: GroupDao,
    private val prefs: UserPrefsRepository
): ViewModel() {

    val selectedGroup: StateFlow<GroupEntity?> =
        prefs.selectedGroupId
            .filterNotNull()
            .flatMapLatest { id ->
                dao.observeById(id)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                null
            )


    init {
        viewModelScope.launch {
            prefs.selectedGroupId
                .filterNotNull()
                .distinctUntilChanged()
                .collect { groupId ->
                    val remote = api.getPartGroupsById(groupId)
                    dao.insert(
                        GroupEntity(
                            id = remote.id!!.toLong(),
                            name = remote.name.orEmpty(),
                            institute = remote.institute.short_name.orEmpty(),
                            course = remote.group
                        )
                    )
                }
        }
    }

}