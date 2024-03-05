package ir.hossein.notekmm.android.presentation.note

import androidx.compose.ui.graphics.Color
import ir.hossein.notekmm.android.core.BaseViewModel
import ir.hossein.notekmm.android.utilities.generateColorList
import ir.hossein.notekmm.android.utilities.log
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
                        empty = notes.isEmpty(),
                        backgroundColor = generateColors(notes.size),
                        loading = false
                    )
                }
            }
        }
    }

    fun deleteNote(note: Note, position: Int) {
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            deleteNoteUseCase(note = note)
            if (stateValue().notes.isEmpty()) updateState { copy(empty = true) }
        }
    }

    private fun toggleLoading() {
        updateState { copy(loading = !stateValue().loading) }
    }

    private fun generateColors(size: Int): List<Color> = generateColorList(size = size)
}