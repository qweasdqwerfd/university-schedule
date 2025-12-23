package com.example.universityschedule.data.local.datastore.sync

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.syncDataStore by preferencesDataStore(
    name = "sync_prefs"
)

class SyncPrefsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val GROUPS_INITIAL_SYNC_DONE =
            booleanPreferencesKey("groups_initial_sync_done")
    }

    suspend fun isGroupsInitialSyncDone(): Boolean {
        return context.syncDataStore.data
            .map { it[GROUPS_INITIAL_SYNC_DONE] ?: false }
            .first()
    }

    suspend fun markGroupsInitialSyncDone() {
        context.syncDataStore.edit {
            it[GROUPS_INITIAL_SYNC_DONE] = true
        }
    }
}
