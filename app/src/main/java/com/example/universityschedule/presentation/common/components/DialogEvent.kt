package com.example.universityschedule.presentation.common.components

sealed class DialogEvent {
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
    data class OnItemClick(val route: String): DialogEvent()
    object onFABClick: DialogEvent()
}