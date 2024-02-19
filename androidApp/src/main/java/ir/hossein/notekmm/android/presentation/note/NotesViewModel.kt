package ir.hossein.notekmm.android.presentation.note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {

    private val _notes = mutableStateOf(NotesUiState())
    val notes = _notes

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase().collect {
                _notes.value = notes.value.copy(notes = it)
            }
        }
    }
}