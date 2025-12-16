package com.example.universityschedule.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class UserPrefsRepository(private val context: Context) {

    companion object {
        private val GROUP_ID = intPreferencesKey("group_id")
    }

    val selectedGroupId: Flow<Int?> = context.dataStore.data
        .map { prefs -> prefs[GROUP_ID] }

    suspend fun saveGroupId(id: Int) {
        context.dataStore.edit { prefs ->
            prefs[GROUP_ID] = id
            Log.d("DBG", "Prefs saved id=$id")
        }
    }

    suspend fun getSelectedGroupId(): Int? {
        return context.dataStore.data
            .map { prefs -> prefs[GROUP_ID] }
            .first()
    }

}
