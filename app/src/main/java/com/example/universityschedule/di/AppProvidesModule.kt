package com.example.universityschedule.di

import android.content.Context
import androidx.room.Room
import com.example.universityschedule.data.local.MainDB
import com.example.universityschedule.data.remote.service.LessonsApiService
import com.example.universityschedule.data.repository.TaskRepositoryImpl
import com.example.universityschedule.domain.repository.TaskRepository
import com.example.universityschedule.presentation.navigation.UIManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideLessonsApi(): LessonsApiService {
        return Retrofit.Builder()
            .baseUrl("https://timetable.bstu.ru/api/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LessonsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskRepo(db: MainDB): TaskRepository = TaskRepositoryImpl(db.taskDao)

    @Provides
    @Singleton
    fun provideNavigationManager() = UIManager()
}