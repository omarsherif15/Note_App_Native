package com.example.noteapp.feature_notes.presentation.add_edit_notes.view_model

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.feature_notes.domain.model.Note
import com.example.noteapp.feature_notes.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val notesUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteContent = mutableStateOf("")
    val noteContent: State<String> = _noteContent

    private val _noteColor = mutableIntStateOf(Note.NoteColor.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1){
                viewModelScope.launch(Dispatchers.IO) {
                    notesUseCases.getNoteById(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = note.title
                        _noteContent.value = note.content
                        _noteColor.intValue = note.color

                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }

    fun onEvent(event: AddEditNotesEvents){
        when(event){
            is AddEditNotesEvents.SaveNote -> {
                val note = Note(
                    title = noteTitle.value,
                    content = noteContent.value,
                    timestamp = System.currentTimeMillis(),
                    color = noteColor.value,
                    id = currentNoteId
                )
                viewModelScope.launch {
                    notesUseCases.addNote(note)
                    _eventFlow.emit(UiEvent.SaveNote)
                }
            }

            is AddEditNotesEvents.AddContent -> {
                _noteContent.value = event.content
            }

            is AddEditNotesEvents.AddTitle -> {
                _noteTitle.value = event.title
            }

            is AddEditNotesEvents.ChangeColor -> {
                _noteColor.intValue = event.color
            }
        }
    }

}