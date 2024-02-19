package ir.hossein.notekmm.domain.usecase.implementation

import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.repository.NoteRepository
import ir.hossein.notekmm.domain.usecase.AddNoteUseCase

class AddNoteUseCaseImpl(private val noteRepository: NoteRepository) : AddNoteUseCase {

    override suspend operator fun invoke(note: Note) {
        noteRepository.addNote(note = note)
    }
}