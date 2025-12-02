package com.example.universityschedule

import android.app.Application
import androidx.room.Room
import com.example.universityschedule.data.local.MainDB
import com.example.universityschedule.data.remote.service.DictApiService
import com.example.universityschedule.data.repository.GroupsRepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.getValue

@HiltAndroidApp
class App: Application() {}