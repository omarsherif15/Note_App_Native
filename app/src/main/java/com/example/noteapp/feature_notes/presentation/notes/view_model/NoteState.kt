package com.example.noteapp.feature_notes.presentation.notes.view_model

import com.example.noteapp.feature_notes.domain.model.Note
import com.example.noteapp.feature_notes.domain.utils.NoteOrder
import com.example.noteapp.feature_notes.domain.utils.OrderBy

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderBy.Ascending),
    val isOrderSectionVisible: Boolean = false,
)
