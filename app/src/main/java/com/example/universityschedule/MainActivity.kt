package com.example.universityschedule

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import com.example.universityschedule.castom_components.UniversityScheduleTheme
import com.example.universityschedule.ui_insruments.DarkColorScheme
import com.example.universityschedule.ui_insruments.LightColorScheme
import com.example.universityschedule.ui_insruments.MyTypography
import com.google.ai.client.generativeai.type.content

class MainActivity() : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniversityScheduleTheme {
                MainScreen()
            }
        }
    }
}

