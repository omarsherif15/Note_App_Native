package com.example.noteapp.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.*

@Entity
data class Note(
    @PrimaryKey val id : Int? = null,
    val title : String,
    val content : String,
    val timestamp : Long,
    val color : Int
){
    companion object{
        val NoteColor = listOf(
            RedOrange, RedPink, BabyBlue, Violet, LightGreen
        )
    }
}

class InvalidNoteException(message : String) : Exception(message)
