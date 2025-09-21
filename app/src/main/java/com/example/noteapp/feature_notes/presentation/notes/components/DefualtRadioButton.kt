package com.example.noteapp.feature_notes.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.feature_notes.domain.utils.NoteOrder
import com.example.noteapp.feature_notes.domain.utils.OrderBy
import com.example.noteapp.ui.theme.NoteAppTheme

@Preview(showBackground = true)
@Composable
fun DefaultRadioButtonPreview(modifier: Modifier = Modifier) {
    NoteAppTheme {
        DefaultRadioButton(
            title = "Title",
            isSelected = false,
            onSelect = {}
        )
    }
}

@Composable
fun DefaultRadioButton(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onSelect: (title: String) -> Unit
) {
    Row(
        Modifier
            .height(40.dp)
            .selectable(
                selected = isSelected,
                onClick = { onSelect(title) },
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        RadioButton(
            modifier = Modifier.padding(0.dp),
            selected = isSelected,
            onClick = { onSelect(title) } // null recommended for accessibility with screen readers
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(0.dp)
        )
    }
}