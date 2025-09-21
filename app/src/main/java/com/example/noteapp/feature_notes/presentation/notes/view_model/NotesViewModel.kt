package com.example.noteapp.feature_notes.presentation.notes.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.feature_notes.domain.model.Note
import com.example.noteapp.feature_notes.domain.use_cases.NoteUseCases
import com.example.noteapp.feature_notes.domain.utils.NoteOrder
import com.example.noteapp.feature_notes.domain.utils.OrderBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    val notesUseCases: NoteUseCases
) : ViewModel(){

    private val _state = mutableStateOf<NoteState>(NoteState())
    val state = _state
    private var recentlyDeletedNote : Note?  = null
    private var GetNotesJob : Job? = null

    init {
        getNotes(
            noteOrder = NoteOrder.Date(OrderBy.Descending)
        )
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.NoteOrderChange -> {
                if (_state.value.noteOrder :: class == event.noteOrder::class &&
                    state.value.noteOrder.orderBy == event.noteOrder.orderBy){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !_state.value.isOrderSectionVisible
                )
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        GetNotesJob?.cancel()
        GetNotesJob = notesUseCases.getNote(noteOrder)
            .onEach { notes ->
                _state.value = _state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}