package com.example.universityschedule.presentation.screens.tasks.details

import com.example.universityschedule.presentation.common.DialogEvent

sealed class DetailsEvent {
    object Edit: DetailsEvent()
    object Delete: DetailsEvent()
    object OnCancel: DetailsEvent()
    object OnConfirm: DetailsEvent()
}