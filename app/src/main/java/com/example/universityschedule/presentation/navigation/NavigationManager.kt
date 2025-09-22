package com.example.universityschedule.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class NavigationCommand {
    data class To(val route: String) : NavigationCommand()
    object Back : NavigationCommand()
}

@Singleton
class NavigationManager @Inject constructor() {

    private val _commands = MutableSharedFlow<NavigationCommand>()
    val commands: SharedFlow<NavigationCommand> = _commands.asSharedFlow()

    suspend fun navigate(command: NavigationCommand) {
        _commands.emit(command)
    }

    suspend fun navigateTo(route: String) = navigate(NavigationCommand.To(route))
    suspend fun navigateBack() = navigate(NavigationCommand.Back)
}