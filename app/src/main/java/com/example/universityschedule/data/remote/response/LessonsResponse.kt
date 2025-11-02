package com.example.universityschedule.data.remote.response

import com.example.universityschedule.data.remote.dto.PublicLesson
import com.example.universityschedule.data.remote.dto.PublicStaticLesson

data class LessonsResponse(
    val lessons: List<PublicStaticLesson>,
    val events: List<PublicLesson>,
)