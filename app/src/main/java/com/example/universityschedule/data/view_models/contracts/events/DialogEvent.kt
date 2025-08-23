package com.example.universityschedule.data.view_models.contracts.events

sealed class DialogEvent {
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}