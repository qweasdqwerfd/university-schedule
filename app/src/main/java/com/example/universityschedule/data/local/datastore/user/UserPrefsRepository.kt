package com.example.universityschedule.data.local.datastore.user

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.userDataStore by preferencesDataStore(
    name = "user_prefs"
)

class UserPrefsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val GROUP_ID = intPreferencesKey("group_id")
    }

    val selectedGroupId: Flow<Int?> =
        context.userDataStore.data
            .map { it[GROUP_ID] }

    suspend fun getSelectedGroupId(): Int? =
        selectedGroupId.first()

    suspend fun saveGroupId(id: Int) {
        context.userDataStore.edit {
            it[GROUP_ID] = id
        }
    }
}
