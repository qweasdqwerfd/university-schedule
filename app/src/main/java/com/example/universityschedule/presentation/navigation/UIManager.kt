package com.example.universityschedule.presentation.navigation

import androidx.compose.material3.SnackbarDuration
import com.example.universityschedule.presentation.common.snack_bar.SnackBarType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class NavigationCommand {
    data class To(val route: String) : NavigationCommand()
    object Back : NavigationCommand()
}

data class SnackBarCommand(
    val message: String,
    val type: SnackBarType = SnackBarType.INFO,
    val duration: SnackbarDuration,
)

@Singleton
class UIManager @Inject constructor() {

    private val _commands = MutableSharedFlow<NavigationCommand>(
        replay = 0,                                                                                 //fix bug
        extraBufferCapacity = 1
    )
    val commands: SharedFlow<NavigationCommand> = _commands.asSharedFlow()

    suspend fun navigate(command: NavigationCommand) {
        _commands.emit(command)
    }

    suspend fun navigateTo(route: String) = navigate(NavigationCommand.To(route))
    suspend fun navigateBack() = navigate(NavigationCommand.Back)

    private val _snackBarCommands = MutableSharedFlow<SnackBarCommand>()
    val snackBarCommands: SharedFlow<SnackBarCommand> = _snackBarCommands

    suspend fun sendSnackBar(
        message: String,
        type: SnackBarType = SnackBarType.INFO,
        duration: SnackbarDuration
    ) =
        _snackBarCommands.emit(SnackBarCommand(message, type, duration))
}