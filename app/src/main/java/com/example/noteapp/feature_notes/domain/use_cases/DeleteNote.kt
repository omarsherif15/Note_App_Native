package com.example.noteapp.feature_notes.domain.use_cases

import com.example.noteapp.feature_notes.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
}