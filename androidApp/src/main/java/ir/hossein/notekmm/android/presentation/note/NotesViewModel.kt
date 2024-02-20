package ir.hossein.notekmm.android.presentation.note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
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
}