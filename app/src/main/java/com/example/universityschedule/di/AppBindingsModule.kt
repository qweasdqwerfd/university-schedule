package com.example.universityschedule.di

import com.example.universityschedule.data.repository.LessonsRepositoryImpl
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.TaskDialogController
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.TaskDialogControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppBindingsModule {

    @Binds
    @Singleton
    fun bindLessonsRepository(
        impl: LessonsRepositoryImpl
    ): LessonsRepository

    @Binds
    @Singleton
    abstract fun bindTaskDialogController(
        impl: TaskDialogControllerImpl
    ): TaskDialogController

}
