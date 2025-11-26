package com.example.universityschedule.presentation.screens.tasks.details

sealed class CRUDEvent {
    object Edit : CRUDEvent()
    object Delete : CRUDEvent()
    object OnCancel : CRUDEvent()
    object OnConfirm : CRUDEvent()
}