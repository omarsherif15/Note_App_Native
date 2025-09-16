package com.example.noteapp.feature_notes.domain.utils

sealed class NoteOrder(val orderBy: OrderBy) {
    class Title(orderBy: OrderBy) : NoteOrder(orderBy)
    class Date(orderBy: OrderBy) : NoteOrder(orderBy)
    class Color(orderBy: OrderBy) : NoteOrder(orderBy)
}