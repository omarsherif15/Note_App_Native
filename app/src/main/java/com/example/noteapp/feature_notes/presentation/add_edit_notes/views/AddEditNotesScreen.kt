package com.example.noteapp.feature_notes.presentation.add_edit_notes.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteapp.feature_notes.presentation.add_edit_notes.view_model.AddEditNoteViewModel
import com.example.noteapp.feature_notes.presentation.add_edit_notes.view_model.AddEditNotesEvents
import com.example.noteapp.feature_notes.presentation.add_edit_notes.components.ColorSelector
import com.example.noteapp.feature_notes.presentation.add_edit_notes.components.DefaultTextField
import com.example.noteapp.ui.theme.BabyBlue
import com.example.noteapp.ui.theme.NoteAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>
@Composable
fun AddEditNotesScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    navController: DestinationsNavigator,
    noteId: Int = -1,
    ) {

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when(event) {
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
                is AddEditNoteViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Scaffold (
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNotesEvents.SaveNote)
                },
                shape = CircleShape,
                containerColor = BabyBlue,
                content = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Add note",
                        tint = Color(0xFF000000)
                    )
                }
            )
        }
    ){
        Column(
            modifier
                .fillMaxSize()
                .background(color = Color(viewModel.noteColor.value))
                .padding(16.dp)
                .padding(it)
        ) {
            ColorSelector(
                onColorSelected = { color ->
                    viewModel.onEvent(AddEditNotesEvents.ChangeColor(color))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTextField(
                label = "Title",
                value = viewModel.noteTitle.value,
                labelStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                onValueChange = {
                    viewModel.onEvent(AddEditNotesEvents.AddTitle(it))
                }
            )

            DefaultTextField(
                label = "Description",
                value = viewModel.noteContent.value,
                singleLine = false,
                onValueChange = {
                    viewModel.onEvent(AddEditNotesEvents.AddContent(it))
                }
            )
        }
    }
}