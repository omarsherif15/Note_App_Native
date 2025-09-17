package com.example.noteapp.feature_notes.domain.utils

sealed class NoteOrder(val orderBy: OrderBy) {
    class Title(orderBy: OrderBy) : NoteOrder(orderBy)
    class Date(orderBy: OrderBy) : NoteOrder(orderBy)
    class Color(orderBy: OrderBy) : NoteOrder(orderBy)

    fun copy (orderBy: OrderBy) : NoteOrder {
        return when(this){
            is NoteOrder.Title -> NoteOrder.Title(orderBy)
            is NoteOrder.Date -> NoteOrder.Date(orderBy)
            is NoteOrder.Color -> NoteOrder.Color(orderBy)
        }
    }
}