package ir.hossein.notekmm.android.presentation.addNote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.usecase.AddNoteUseCase
import kotlinx.coroutines.launch

class AddNoteViewModel(private val addNoteUseCase: AddNoteUseCase) : ViewModel() {

    private val _state = mutableStateOf(AddNoteUiState())
    val state = _state

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase(note = note)
        }
    }

    fun updateState(newState: AddNoteUiState) {
        _state.value = newState
    }
}