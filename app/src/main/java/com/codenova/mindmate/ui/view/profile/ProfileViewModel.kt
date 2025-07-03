package com.codenova.mindmate.ui.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    // private val _state = MutableStateFlow(ProfileState())
    // val state = _state.asStateFlow()
    private val _uiEvent = MutableSharedFlow<ProfileUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onHomeClick() {
        viewModelScope.launch {
            _uiEvent.emit(ProfileUiEvent.NavigateToHome)
        }
    }
}