package com.example.universityschedule.domain.usecases

import com.example.universityschedule.presentation.navigation.Screen
import com.example.universityschedule.presentation.navigation.UIManager
import javax.inject.Inject

class FABUseCase @Inject constructor(
    private val uiManager: UIManager
) {
    operator suspend fun invoke() {
        uiManager.navigateTo(Screen.ADD_TASK.route)
    }
}