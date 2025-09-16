package com.example.noteapp.feature_notes.domain.use_cases

import com.example.noteapp.feature_notes.domain.model.Note
import com.example.noteapp.feature_notes.domain.repository.NoteRepository
import com.example.noteapp.feature_notes.domain.utils.NoteOrder
import com.example.noteapp.feature_notes.domain.utils.OrderBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNote (
    private val repository: NoteRepository
){
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderBy.Ascending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { note ->
            when(noteOrder.orderBy){
                is OrderBy.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> note.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> note.sortedBy { it.timestamp }
                        is NoteOrder.Color -> note.sortedBy { it.color }
                    }
                }
                is OrderBy.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> note.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> note.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> note.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}