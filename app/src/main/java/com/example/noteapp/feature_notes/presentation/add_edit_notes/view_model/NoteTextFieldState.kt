package com.example.noteapp.feature_notes.presentation.add_edit_notes.view_model

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
