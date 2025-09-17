package com.example.noteapp.feature_notes.presentation.notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.noteapp.feature_notes.domain.model.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onDeleteClick: () -> Unit,
    onNoteClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ){
        Column {
            Text(
                text = note.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier.height(8.dp))
            Row {
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier.height(8.dp))
                IconButton(
                    onClick = onDeleteClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete note",
                    )
                }
            }
        }
    }
}