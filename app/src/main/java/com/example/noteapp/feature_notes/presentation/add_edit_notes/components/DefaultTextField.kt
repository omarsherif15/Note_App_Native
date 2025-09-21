package com.example.noteapp.feature_notes.presentation.add_edit_notes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.noteapp.ui.theme.BabyBlue
import com.example.noteapp.ui.theme.NoteAppTheme

@Preview(showBackground = true, backgroundColor = 0xff81deea)
@Composable
private fun DefaultTextFieldPreview() {
    NoteAppTheme {
        DefaultTextField(
            label = "Title",
            onValueChange = {},
            value = ""
        )
    }
}

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    label: String,
    labelStyle : TextStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
    maxLines: Int = 1,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    value: String,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder ={ Text(label, style = labelStyle ) } ,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else Int.MAX_VALUE,
        textStyle =
            if (singleLine) TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            else TextStyle(color = Color.Black, fontSize = 16.sp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor =Color.Black ,
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black ,
            focusedTextColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,

        )
    )
}