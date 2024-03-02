package ir.hossein.notekmm.android.presentation.note

import androidx.compose.ui.graphics.Color
import ir.hossein.notekmm.domain.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val loading: Boolean = false,
    val empty: Boolean = true,
    val backgroundColor: List<Color> = emptyList()
)
