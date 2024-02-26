package ir.hossein.notekmm.domain.usecase.implementation

import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.repository.NoteRepository
import ir.hossein.notekmm.domain.usecase.AddNoteUseCase
import ir.hossein.notekmm.domain.usecase.DeleteNoteUseCase

class DeleteNoteUseCaseImpl(private val noteRepository: NoteRepository) : DeleteNoteUseCase {

    override suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note = note)
    }
}