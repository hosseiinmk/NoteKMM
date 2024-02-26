package ir.hossein.notekmm.domain.usecase

import ir.hossein.notekmm.domain.model.Note

interface DeleteNoteUseCase {

    suspend operator fun invoke(note: Note)
}