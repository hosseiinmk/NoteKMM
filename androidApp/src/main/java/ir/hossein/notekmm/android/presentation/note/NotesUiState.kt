package ir.hossein.notekmm.android.presentation.note

import ir.hossein.notekmm.domain.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList()
)
