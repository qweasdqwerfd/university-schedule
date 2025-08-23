package com.example.universityschedule.data.bd

import android.content.Context
import androidx.room.Room
import com.example.universityschedule.data.dao.TaskDao
import com.example.universityschedule.data.repo.LessonRepoImpl
import com.example.universityschedule.data.repo.LessonRepository
import com.example.universityschedule.data.repo.TaskRepoImpl
import com.example.universityschedule.data.repo.TaskRepository
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
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepo(db: MainDB): TaskRepository {
        return TaskRepoImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideLessonRepo(db: MainDB): LessonRepository {
        return LessonRepoImpl(db.lessonDao)
    }
}