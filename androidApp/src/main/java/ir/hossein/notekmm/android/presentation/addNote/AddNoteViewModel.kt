package ir.hossein.notekmm.android.presentation.addNote

import ir.hossein.notekmm.android.core.BaseViewModel
import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.usecase.AddNoteUseCase

class AddNoteViewModel(
    private val addNoteUseCase: AddNoteUseCase
) : BaseViewModel<AddNoteUiState>(AddNoteUiState()) {

    fun addNote(note: Note) {
        baseViewModelScope {
            addNoteUseCase(note = note)
            clearNote()
        }
    }

    private fun clearNote() { updateState { copy(title = "", content = "")} }
}