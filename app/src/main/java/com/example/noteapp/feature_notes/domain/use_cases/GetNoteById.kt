package com.example.noteapp.feature_notes.domain.use_cases

import com.example.noteapp.feature_notes.domain.model.Note
import com.example.noteapp.feature_notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNoteById(
    private val repository: NoteRepository
) {
     operator fun invoke(noteId: Int) : Note? {
       return repository.getNoteById(noteId)
    }
}