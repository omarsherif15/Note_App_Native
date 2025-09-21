package com.example.noteapp.feature_notes.presentation.notes.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.noteapp.R
import com.example.noteapp.feature_notes.presentation.notes.view_model.NotesEvent
import com.example.noteapp.feature_notes.presentation.notes.view_model.NotesViewModel
import com.example.noteapp.feature_notes.presentation.notes.components.NoteItem
import com.example.noteapp.feature_notes.presentation.notes.components.OrderBySection
import com.example.noteapp.ui.theme.BabyBlue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.app.destinations.AddEditNotesScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@Destination<RootGraph>(start = true)
@Composable
fun NotesScreen(
    navController: DestinationsNavigator,
    viewModel : NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                   navController.navigate(AddEditNotesScreenDestination())
                },
                shape = CircleShape,
                containerColor = BabyBlue,
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note",
                        tint = Color(0xFF000000)
                    )
                }
            )
        }
    ){
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Row {
                    Text(
                        text = "Your Notes",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_filter_list_24),
                            contentDescription = "Add note",
                            tint = Color(0xFFFFFFFF)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible
                ) {
                    OrderBySection(
                        modifier = Modifier.fillMaxWidth(),
                        noteOrder = state.noteOrder,
                        onOrderSelected = { noteOrder ->
                            viewModel.onEvent(NotesEvent.NoteOrderChange(noteOrder))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(state.notes.size) { note ->
                        NoteItem(
                            note = state.notes[note],
                            onDeleteClick = {
                                viewModel.onEvent(NotesEvent.DeleteNote(state.notes[note]))
                                scope.launch {
                                    val snackBarResult = snackBarHostState.showSnackbar(
                                        message = "Note deleted",
                                        actionLabel = "Undo",
                                        withDismissAction = true,
                                    )
                                    if(snackBarResult == SnackbarResult.ActionPerformed){
                                        viewModel.onEvent(NotesEvent.RestoreNote)
                                    }
                                }
                            },
                            onNoteClick = {
                                navController.navigate(
                                    AddEditNotesScreenDestination(
                                        noteId = state.notes[note].id ?: -1
                                    ))
                            }
                        )
                    }
                }
            }
        }
    }
}