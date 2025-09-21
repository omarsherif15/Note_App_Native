package com.example.noteapp.feature_notes.presentation.add_edit_notes.view_model

sealed class AddEditNotesEvents {
    object SaveNote : AddEditNotesEvents()
    data class AddTitle(val title: String) : AddEditNotesEvents()
    data class AddContent(val content: String) : AddEditNotesEvents()
    data class ChangeColor(val color: Int) : AddEditNotesEvents()
}