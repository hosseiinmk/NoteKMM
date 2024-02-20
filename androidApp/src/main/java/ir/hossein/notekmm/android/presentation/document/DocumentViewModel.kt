package ir.hossein.notekmm.android.presentation.document

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.android.presentation.document.DocumentUiState
import ir.hossein.notekmm.domain.usecase.GetDocUseCase
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.launch

class DocumentViewModel(
    private val getDocUseCase: GetDocUseCase
) : ViewModel() {

    private val _state = mutableStateOf(DocumentUiState())
    val state = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            getDocUseCase().let {
                _state.value = state.value.copy(document = it)
            }
        }
    }
}