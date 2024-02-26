package ir.hossein.notekmm.android.presentation.note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.usecase.DeleteNoteUseCase
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NotesUiState())
    val state = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase().collect {
                _state.value = state.value.copy(notes = it)
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note = note)
        }
    }
}