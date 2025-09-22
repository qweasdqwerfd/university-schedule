package com.example.universityschedule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

@Composable
fun HandleNavigation(
    navController: NavController,
    navigationFlow: Flow<String>
) {
    LaunchedEffect(Unit) {
        navigationFlow.collect { route ->
            navController.navigate(route) {
                launchSingleTop = true
            }
        }
    }
}
