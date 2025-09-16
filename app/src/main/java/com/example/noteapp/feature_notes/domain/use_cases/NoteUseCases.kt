package com.example.noteapp.feature_notes.domain.use_cases

data class NoteUseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val deleteNote: DeleteNote,
)
