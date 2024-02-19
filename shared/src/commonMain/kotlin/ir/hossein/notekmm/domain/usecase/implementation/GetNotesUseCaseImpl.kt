package ir.hossein.notekmm.domain.usecase.implementation

import ir.hossein.notekmm.domain.usecase.GetNotesUseCase
import ir.hossein.notekmm.domain.model.Note
import ir.hossein.notekmm.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCaseImpl(private val repository: NoteRepository) : GetNotesUseCase {

    override suspend fun invoke(): Flow<List<Note>> = repository.getNotes()
}