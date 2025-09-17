package com.example.noteapp.feature_notes.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.noteapp.feature_notes.domain.utils.NoteOrder
import com.example.noteapp.feature_notes.domain.utils.OrderBy

@Composable
fun OrderBySection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderBy.Descending),
    onOrderSelected : (NoteOrder) -> Unit
    ) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.selectableGroup()
        ) {
            DefaultRadioButton(
                title = "Title",
                isSelected = noteOrder is NoteOrder.Title,
                onSelect = {onOrderSelected(NoteOrder.Title(noteOrder.orderBy))}
            )
            DefaultRadioButton(
                title = "Date",
                isSelected = noteOrder is NoteOrder.Date,
                onSelect = { onOrderSelected(NoteOrder.Date(noteOrder.orderBy))}
            )
            DefaultRadioButton(
                title = "Color",
                isSelected = noteOrder is NoteOrder.Color,
                onSelect = { onOrderSelected(NoteOrder.Color(noteOrder.orderBy))}
            )
        }
        Row(
            modifier = Modifier.selectableGroup()
        ) {
            DefaultRadioButton(
                title = "Ascending",
                isSelected = noteOrder == NoteOrder.Title(OrderBy.Ascending),
                onSelect = {onOrderSelected(noteOrder.copy(OrderBy.Ascending)) }
            )
            DefaultRadioButton(
                title = "Descending",
                isSelected = noteOrder == NoteOrder.Title(OrderBy.Descending),
                onSelect = { onOrderSelected(noteOrder.copy(OrderBy.Descending)) }
            )
        }
    }
}