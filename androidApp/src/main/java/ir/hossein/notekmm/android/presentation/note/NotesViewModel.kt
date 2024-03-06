package ir.hossein.notekmm.android.presentation.note

import ir.hossein.notekmm.android.core.BaseViewModel
import ir.hossein.notekmm.android.utilities.generateColorList
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
        updateState { copy(loading = true) }
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            getNotesUseCase().collect { notes ->
                updateState {
                    copy(
                        notes = notes,
                        empty = notes.isEmpty()
                    )
                }
                setTheme(notes.size)
            }
        }
    }

    fun deleteNote(note: Note) {
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            deleteNoteUseCase(note = note)
        }
    }

    fun setTheme(size: Int) {
        updateState { copy(loading = true) }
        generateColorList(size = size).let { colors ->
            updateState {
                copy(backgroundColor = colors, loading = false)
            }
        }
    }
}