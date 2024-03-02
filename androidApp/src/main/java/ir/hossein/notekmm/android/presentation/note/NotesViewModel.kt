package ir.hossein.notekmm.android.presentation.note

import ir.hossein.notekmm.android.core.BaseViewModel
import ir.hossein.notekmm.android.utilities.generateRandomColorList
import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.usecase.DeleteNoteUseCase
import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.Dispatchers

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : BaseViewModel<NotesUiState>(NotesUiState()) {

    init {
        getNotes()
    }

    private fun getNotes() {
        toggleLoading()
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            getNotesUseCase().collect { notes ->
                updateState {
                    copy(
                        notes = notes,
                        backgroundColor = generateRandomColorList(itemsSize = notes.size),
                        loading = false,
                        empty = notes.isEmpty()
                    )
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        baseViewModelScope {
            deleteNoteUseCase(note = note)
            if (stateValue().notes.isEmpty()) updateState { copy(empty = true) }
        }
    }

    private fun toggleLoading() {
        updateState { copy(loading = !stateValue().loading) }
    }
}