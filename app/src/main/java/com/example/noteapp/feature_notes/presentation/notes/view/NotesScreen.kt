package com.example.noteapp.feature_notes.presentation.notes.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.noteapp.feature_notes.presentation.notes.NotesViewModel
import com.example.noteapp.ui.theme.BabyBlue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination<RootGraph>(start = true)
@Composable
fun NotesScreen(
    navController: DestinationsNavigator,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row {
                Text(
                    text = "Your Notes",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    //navController.navigate(NotesScreenDestination())
                } ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Add note",
                        tint = Color(0xFF000000)
                    )
                }
            }
        }
    }
}