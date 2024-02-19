package ir.hossein.notekmm.domain.usecase

import ir.hossein.notekmm.domain.model.Note

interface AddNoteUseCase {

    suspend operator fun invoke(note: Note)
}