package com.example.universityschedule.di

import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.TaskDialogController
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.TaskDialogControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TaskDialogModule {

    @Binds
    @Singleton
    abstract fun bindTaskDialogController(
        impl: TaskDialogControllerImpl
    ): TaskDialogController
}
