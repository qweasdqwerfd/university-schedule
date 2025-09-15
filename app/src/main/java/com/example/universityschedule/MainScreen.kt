package com.example.universityschedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.universityschedule.presentation.bottom_bar.BottomNavigation
import com.example.universityschedule.presentation.navigation.NavGraph
import com.example.universityschedule.presentation.top_bar.TopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {

    val navController = rememberNavController()


    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomNavigation(navController) }


    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navController


            )
        }

    }
}