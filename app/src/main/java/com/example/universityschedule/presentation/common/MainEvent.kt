package com.example.universityschedule.presentation.common

sealed class DialogEvent {
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
    data class OnItemClick(val id: Int): DialogEvent()
    object OnButtonClick: DialogEvent()
    object OnFABClick: DialogEvent()
    data class OnCircleClick(val id: Int): DialogEvent()
}