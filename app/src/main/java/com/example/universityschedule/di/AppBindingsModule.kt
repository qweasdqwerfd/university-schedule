package com.example.universityschedule.di

import com.example.universityschedule.data.repository.DictRepositoryImpl
import com.example.universityschedule.data.repository.GroupsRepositoryImpl
import com.example.universityschedule.data.repository.LessonsRepositoryImpl
import com.example.universityschedule.domain.repository.DictRepository
import com.example.universityschedule.domain.repository.GroupsRepository
import com.example.universityschedule.domain.repository.LessonsRepository
import com.example.universityschedule.presentation.common.dialog_controller.DialogController
import com.example.universityschedule.presentation.common.dialog_controller.DialogControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindingsModule {

    @Binds
    @Singleton
    abstract fun bindLessonsRepository(
        impl: LessonsRepositoryImpl
    ): LessonsRepository

    @Binds
    @Singleton
    abstract fun bindDictRepository(
        impl: DictRepositoryImpl
    ): DictRepository

    @Binds
    @Singleton
    abstract fun bindTaskDialogController(
        impl: DialogControllerImpl
    ): DialogController

    @Binds
    @Singleton
    abstract fun bindGroupRepository(
        impl: GroupsRepositoryImpl
    ): GroupsRepository

}