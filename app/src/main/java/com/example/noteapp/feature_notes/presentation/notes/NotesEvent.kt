package com.example.noteapp.feature_notes.presentation.notes

import com.example.noteapp.feature_notes.domain.model.Note
import com.example.noteapp.feature_notes.domain.utils.NoteOrder

sealed class NotesEvent {
    data class NoteOrderChange(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object ToggleOrderSection : NotesEvent()
    object RestoreNote : NotesEvent()
}