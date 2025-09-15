package com.example.universityschedule.di

import android.content.Context
import androidx.room.Room
import com.example.universityschedule.data.local.MainDB
import com.example.universityschedule.data.repository.LessonRepositoryImpl
import com.example.universityschedule.domain.repository.LessonRepository
import com.example.universityschedule.data.repository.TaskRepositoryImpl
import com.example.universityschedule.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideTaskRepo(db: MainDB): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideLessonRepo(db: MainDB): LessonRepository {
        return LessonRepositoryImpl(db.lessonDao)
    }


}