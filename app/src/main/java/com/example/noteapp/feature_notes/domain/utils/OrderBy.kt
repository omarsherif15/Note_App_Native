package com.example.noteapp.feature_notes.domain.utils

sealed class OrderBy {
    object Ascending : OrderBy()
    object Descending : OrderBy()
}