package com.example.noteapp.feature_notes.presentation.add_edit_notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.noteapp.feature_notes.domain.model.Note

@Composable
fun ColorSelector(
    modifier: Modifier = Modifier,
    onColorSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Note.NoteColor.forEach { color ->
            Box(
                modifier = Modifier.size(60.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(color)

                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = CircleShape
                    )

                    .clickable(
                        onClick = {
                            onColorSelected(color.toArgb())
                        }
                    )
                )
        }
    }
}