package com.example.universityschedule.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.universityschedule.data.local.dao.GroupDao
import com.example.universityschedule.data.local.MainDB
import com.example.universityschedule.data.local.datastore.UserPrefsRepository
import com.example.universityschedule.data.repository.TaskRepositoryImpl
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppProvidesModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDB {
        return Room.databaseBuilder(
            context,
            MainDB::class.java,
            "university_schedule_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskRepo(db: MainDB): TaskRepository = TaskRepositoryImpl(db.taskDao)

    @Provides
    @Singleton
    fun provideUIManager() = UIManager()

    @Provides
    @Singleton
    fun provideUserPrefsRepository(@ApplicationContext context: Context) = UserPrefsRepository(context)

    @Provides
    fun provideGroupDao(db: MainDB): GroupDao = db.groupDao

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)
}